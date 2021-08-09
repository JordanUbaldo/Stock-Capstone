<template>
  <div>
      <!-- Game Name -->
      <h1>Game: {{ $store.state.currentGameName }}</h1>
      <!-- End Date -->
      <h2>End Date: {{ (new Date($store.state.currentGameEndDate)).toLocaleString() }}</h2>
      <!-- Invite Button for Host -->
      <button class="btn" v-if="displayInvite" type="button" @click='routeToInvite'>Invite</button>
  </div>
</template>

<script>
import userService from '@/services/UserService.js';

export default {
    name: "game-summary",

    computed: {
      displayInvite(){
          const currentGame= this.$store.state.acceptedGames.find(element => element.gameId === this.$store.state.currentGameId);
          if(currentGame.host === this.$store.state.user.username && !this.$route.name.match('game-over')) {
            return true;
          } else {
            return false;
          }
      }
    },
    methods: {
      routeToInvite() {
        userService.getUsersForGame(this.$store.state.currentGameId, this.$store.state.token)
        .then(response => {
            if (response.status === 200) {
                this.$store.commit("SET_CURRENT_GAME_USERS", response.data)
            }
        });
        this.$router.push({ name: 'invite', params: { gameId : this.$store.state.currentGameId}})
      }
    }

}
</script>

<style>

</style>