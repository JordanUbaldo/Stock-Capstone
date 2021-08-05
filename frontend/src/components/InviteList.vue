<template>
  <div>
      <h3>Invites</h3>
      <ul>
          <li v-for="invite in invited" v-bind:key="invite.gameId">
            {{invite.gameName}}
            <button @click.prevent="accept(invite.gameId)">Accept</button>
            <button @click="decline">Decline</button>
          </li>
      </ul>
  </div>
</template>

<script>
import gameService from '@/services/GamesService.js';

export default {
    name: "invite-list",
    data() {
        return {
        }
    },
    computed: {
        invited() {
            return this.$store.state.invites
        }
    },
    methods: {
        accept(gameId){
            const invite= { status: "Accepted", username: this.$store.state.user.username}
            console.log(invite)
            gameService.gameInvite(gameId,invite,this.$store.state.token)
            .then(response => {
                if (response.status === 201){
                    console.log("Accepted")
                }
            })
        },
        decline(){
            
        }
    }
}
</script>

<style>

</style>