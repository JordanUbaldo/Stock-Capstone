<template>
  <div>
  <div>
    <div v-if="$route.name.match('game-over')">
      <winner-display v-bind:winner="winner" />
    </div>
      <h2 v-if="!$route.name.match('game-over')">Current Position</h2>
      <table v-if="!$route.name.match('game-over')">
        <tr class="highlight">
          <td>{{  userIndex+1 }}</td>
          <td>{{ fullLeaderboard[userIndex].username }}</td>
          <td>${{ new Intl.NumberFormat().format(fullLeaderboard[userIndex].amount) }}</td>
        </tr>
      </table>
    </div>
    <div>
      <h2>Leaderboard</h2>
        <table>
            <tr td v-bind:class="(user.username===$store.state.user.username)?'highlight':''" v-bind:key="user.username" v-for="user in fullLeaderboard">
                <td>{{ fullLeaderboard.indexOf(user)+1 }}</td>
                <td>{{ user.username }}</td>
                <td>${{ new Intl.NumberFormat().format(user.amount) }}</td>
            </tr>
        </table>
        <br>
    </div>
  </div> 
</template>

<script>
import winnerDisplay from '@/components/games/WinnerDisplay'
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
.highlight {
  color: gold;
}

hr {
  border: 1px solid #eeeeee;
}
</style>