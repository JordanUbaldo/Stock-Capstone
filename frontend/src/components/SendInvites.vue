<template>
    <div>
        <ul>
            <li v-for="user in users" v-bind:key="user.username">
            {{user.username}}
            <button @click="invitePlayer(user.username)">Invite</button>
            </li>
        </ul>
    </div>
</template>

<script>
import gameService from '@/services/GamesService';
export default {
    data() {
        return {
        };

    },
    computed: {
        users() {
            let uninvited;
            this.$store.state.currentGameUsers.forEach(currentUser => {
                uninvited = this.$store.state.allUsers.filter(user => {
                    return user.username != currentUser.username;
                });
            })
            return uninvited;
        }
    },
    methods: {
        invitePlayer(user){
            const invite= { status: "Pending", username: user}
            gameService.gameInvite(this.$store.state.currentGameId,invite,this.$store.state.token)
            .then(response => {
                if (response.status === 201){
                    alert("Invite Sent")
                }
            })

        }
    }
}
</script>

<style>

</style>