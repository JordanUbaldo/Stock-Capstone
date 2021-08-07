<template>
  <div>
    <h2>Leaderboard</h2>
    <p>{{ leaderboard }}</p>
  </div> 
</template>

<script>
import gamesService from '../services/GamesService'
export default {
    name: "leader-board",
    data() {
      return {
        leaderboard: {}
      }
    },
    async created() {
      let response = await gamesService.getLeaderboard(this.$store.state.currentGameId, this.$store.state.token);
      this.leaderboard = response.data;
    }
}
</script>

<style>

</style>