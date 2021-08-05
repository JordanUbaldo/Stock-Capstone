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
            invite: {
                playStatus: "Pending",
                username: ""
            }
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
        invitePlayer(username){
            this.username = username
            gameService.gameInvite(this.invite,this.$store.state.token)
            .then(response => {
                if (response.status === 200){
                    alert("Invite Sent")
                }
            })

        }
    }
}
</script>

<style>

</style>