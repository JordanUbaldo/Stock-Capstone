<template>
  <div>
      <!-- Game Name -->
      <p>{{ $store.state.currentGameName }}</p>
      <!-- End Date -->
      <p>End Date</p>
      <!-- Invite Button for Host -->
      <button type="button" @click='routeToInvite'>Invite</button>
  </div>
</template>

<script>
import userService from '@/services/UserService.js';

export default {
    name: "game-summary",

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