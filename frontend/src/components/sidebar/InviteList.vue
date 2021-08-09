<template>
  <div>
      <h2>Invites</h2>
      <ul>
          <li class="invite-list" v-for="invite in invited" v-bind:key="invite.gameId">
            {{invite.gameName}}
            <button class="sb-btn" @click="accept(invite.gameId)">Accept</button>
            <button class="sb-btn" @click="decline(invite.gameId)">Decline</button>
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
            return this.$store.state.invites.filter(invitedGame => {
                return !this.isGameOver(invitedGame.endDate);
            })
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
                    gameService
                        .getGames(this.$store.state.token, "Accepted")
                        .then(response => {
                            if(response.status === 200) {
                                this.$store.commit("SET_ACCEPTED_GAMES", response.data);
                            }
                        })

                    gameService
                        .getGames(this.$store.state.token, "Pending")
                        .then(response => {
                            if(response.status === 200) {
                                this.$store.commit("SET_INVITES", response.data);
                            }
                        })
                }
            })
        },
        decline(gameId){
            const invite= { status: "Declined", username: this.$store.state.user.username};
            console.log(invite);
            gameService.gameInvite(gameId,invite,this.$store.state.token)
                .then(response => {
                    if (response.status === 201){
                        console.log("Declined");
                        gameService
                            .getGames(this.$store.state.token, "Accepted")
                            .then(response => {
                                if(response.status === 200) {
                                    this.$store.commit("SET_ACCEPTED_GAMES", response.data);
                                }
                            })

                        gameService
                            .getGames(this.$store.state.token, "Pending")
                            .then(response => {
                                if(response.status === 200) {
                                    this.$store.commit("SET_INVITES", response.data);
                                }
                            })
                    }
                })
        },
        isGameOver(endDate) {
            const marketCloseTime = "T16:00:00";
            const end = new Date(endDate + marketCloseTime);

            return Date.now() - end.getTime() >= 0;
        }
    } 
}
</script>

<style>

</style>