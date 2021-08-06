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
            currentElement: ""
        };

    },
    computed: {
        users() {
            let allUsers = this.$store.state.allUsers;
            let currentGameUsers = this.$store.state.currentGameUsers;

            return allUsers.filter(user => {
                return !currentGameUsers.map(u => u.username).includes(user.username);
            })
        }
    },
    methods: {
        invitePlayer(user){
            const invite= { status: "Pending", username: user}
            gameService.gameInvite(this.$store.state.currentGameId, invite, this.$store.state.token)
            .then(response => {
                if (response.status === 201){
                    alert("Invite Sent")
                     this.$store.commit("ADD_USER_TO_GAME", {status: "Pending", username: user, gameId: this.$store.currentGameId});
                }
            })
        }
    }
}
</script>

<style>

</style>