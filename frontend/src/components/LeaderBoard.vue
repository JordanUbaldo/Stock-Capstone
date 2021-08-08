<template>
  <div>
  <div>
    <div v-if="$route.name.match('game-over')">
      <winner-display v-bind:winner="winner" />
    </div>
      <h2>Leaderboard</h2>
      <p>You:</p>
      <p>{{  userIndex+1 }}  {{ userDisplay.username }}  ${{ userDisplay.amount }}</p>
      <p></p>
      <p></p>
    </div>
    <p>---------------------------</p>
    <div v-bind:key="user.username" v-for="user in fullLeaderboard">
    <p>{{ fullLeaderboard.indexOf(user)+1 }}  {{ user.username }}  ${{ user.amount }}</p>
    <p></p>
    </div>
  </div> 
</template>

<script>
import gamesService from '../services/GamesService'
import winnerDisplay from '@/components/WinnerDisplay'
export default {
    name: "leader-board",
    components: {
      winnerDisplay
    },
    data() {
      return {
        fullLeaderboard: [],
        leaderboardTop10: [],
        userIndex: 0,
        userDisplay: {},
        winner: {}
      }
    },
    async created() {
      let response = await gamesService.getLeaderboard(this.$store.state.currentGameId, this.$store.state.token);
      this.fullLeaderboard = response.data.sort((a,b) => b.amount - a.amount)
      this.userIndex = this.fullLeaderboard.findIndex(user => user.username === this.$store.state.user.username);
      this.userDisplay = this.fullLeaderboard[this.userIndex];
      this.winner = this.fullLeaderboard[0];
    }
}
</script>

<style>


</style>