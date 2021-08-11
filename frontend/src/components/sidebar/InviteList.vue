<template>
  <div>
      <h2 v-show="!invited.length < 1">Invites</h2>
      <ul>
          <li id="inv-list" v-for="invite in invited" v-bind:key="invite.gameId">
            {{invite.gameName}}
            <hr>
            <button id="inv-accept" @click="accept(invite.gameId)">Accept</button>
            <button id="inv-decline" @click="decline(invite.gameId)">Decline</button>
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
#inv-list {
    display: flex;
    flex-flow: wrap;
    justify-content: center;
    margin: 5px 30px 5px 30px;
    padding: 10px 0px 10px 0px;
    background-color: #2f8f4f;
    border-radius: 5px;
    border: 1px solid #033a0c;
    font-size: 16px;
    color: #eeeeee;
}

#inv-list hr {
    flex-basis: 100%;
    height: 0;
    margin: 0;
    border: 0;
}

#inv-accept, #inv-decline {
    justify-content: center;
    margin: 3px;
    padding: 5px 0px 5px 0px;
    width: 60px;
    background-color: #eeeeee;
    border-radius: 5px;
    border: 1px solid #c4c4c4;
    font-size: 16px;
    color: #30642e;
}

#inv-accept:hover, #inv-decline:hover {
    background-color: #cccccc;
    cursor: pointer;
}
</style>