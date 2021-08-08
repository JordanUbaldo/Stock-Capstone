<template>
  <div>
  <div>
    <div v-if="$route.name.match('game-over')">
      <winner-display v-bind:winner="winner" />
    </div>
      <h2>Leaderboard</h2>
      <p>You:</p>
      <p>{{  userIndex+1 }}  {{ fullLeaderboard[userIndex].username }}  ${{ new Intl.NumberFormat().format(fullLeaderboard[userIndex].amount) }}</p>
      <p></p>
      <p></p>
    </div>
    <p>---------------------------</p>
    <div v-bind:key="user.username" v-for="user in fullLeaderboard">
    <p>{{ fullLeaderboard.indexOf(user)+1 }}  {{ user.username }}  ${{ new Intl.NumberFormat().format(user.amount) }}</p>
    <p></p>
    </div>
  </div> 
</template>

<script>
import winnerDisplay from '@/components/WinnerDisplay'
export default {
    name: "leader-board",
    components: {
      winnerDisplay
    },
    computed: {
      fullLeaderboard(){
        return this.$store.state.currentLeaderboard
      },
      userIndex(){
        const userIndex = this.$store.state.currentLeaderboard.findIndex(user => user.username === this.$store.state.user.username);
        const portfolio = this.$store.state.currentLeaderboard[userIndex].amount
        this.$store.commit("SET_USER_PORTFOLIO_BALANCE", portfolio);
        return userIndex;
      },
      winner(){
        return this.$store.state.currentLeaderboard[0];
      }
    }
}
</script>

<style>


</style>