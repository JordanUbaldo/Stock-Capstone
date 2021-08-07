<template>
  <div>
    <h2>Leaderboard</h2>
    <div>
      <p>You:</p>
      <p>{{userRank }}</p>
      <p>{{ user.username }}</p>
      <p>{{ user.amount }}</p>
    </div>
    <p>---------------------------</p>
    <div v-bind:key="user.username" v-for="user in leaderboard">
    <p>{{ leaderboard.indexOf(user)+1 }}</p>
    <p>{{ user.username }}</p>
    <p>{{ user.amount }}</p>
    </div>
  </div> 
</template>

<script>
import gamesService from '../services/GamesService'
export default {
    name: "leader-board",
    data() {
      return {
        leaderboard: [],
        user: {},
        userRank: 0
      }
    },
    async created() {
      let response = await gamesService.getLeaderboard(this.$store.state.currentGameId, this.$store.state.token);
      this.leaderboard = response.data.sort((a,b) => b.age - a.age)
      this.user = response.data.find(user => user.username = this.$store.state.user.username);
      this.userRank = response.data.indexOf(this.user) +1
    }
}
</script>

<style>

</style>