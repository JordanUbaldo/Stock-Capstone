<template>
    <div>
        <ul>
            <li v-for="user in users" v-bind:key="user.username">
            {{user.username}}
            <button @click="invitePlayer(user.username)">Invite</button>
            </li>
            <p>{{users}}</p>
            <p>123</p>
            <p>{{currentGameUsers}}</p> 
        </ul>
    </div>
</template>

<script>
import gameService from '@/services/GamesService';
import userService from '@/services/UserService';
export default {
    data() {
        return {
        };

    },
    computed: {
        users() {
            let allUsers = this.$store.state.allUsers;
            let currentGameUsers = this.$store.state.currentGameUsers;

            return allUsers.filter(user => {
                return !currentGameUsers.map(u => u.username).includes(user.username);
            })
        },
        currentGameUsers(){
            return this.$store.state.currentGameUsers
        }
    },
    methods: {
        invitePlayer(user){
            const invite= { status: "Pending", username: user}
            gameService.gameInvite(this.$store.state.currentGameId,invite,this.$store.state.token)
            .then(response => {
                if (response.status === 201){
                    alert("Invite Sent")
                               userService.getUsersForGame(this.$store.state.currentGameId, this.$store.state.token)
                    .then(response => {
                    if (response.status === 200) {
                    this.$store.commit("SET_CURRENT_GAME_USERS", response.data)
                    }
                    });
                }
            })
        }
    }
}
</script>

<style>

</style>