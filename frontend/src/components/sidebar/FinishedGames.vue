<template>
  <div>    
    <h2>Finished Games</h2>
      <ul>
        <li class="game-list" v-for="game in games" v-bind:key="game.gameId" @click="routeToFinishedGameView(game.gameId, game.gameName, game.endDate)">{{ game.gameName }}<img class="hostImage" v-show="game.host == $store.state.user.username" src="@/assets/Crown.png" alt="Host Image"></li>
      </ul>
  </div>
</template>

<script>
import gamesService from "@/services/GamesService";
export default {
  name: "finished-games",
  computed: {
      games() {
        return this.$store.state.acceptedGames.filter(game => {
          return this.isGameOver(game.endDate);
        })
      },
  },
  methods: {
    async routeToFinishedGameView(gameId, gameName, gameEndDate){
        if(this.$route.path != `/game/${gameId}/game-over`) {
        this.$store.commit("SET_CURRENT_GAME_ID", gameId);
        this.$store.commit("SET_CURRENT_GAME_NAME", gameName);
        this.$store.commit('SET_CURRENT_GAME_END_DATE', gameEndDate);

        const response = await gamesService.getLeaderboard(this.$store.state.currentGameId, this.$store.state.token);
        const leaderboard= response.data.sort((a,b) => b.amount - a.amount)
        this.$store.commit('SET_CURRENT_LEADERBOARD', leaderboard);

        this.$router.push({ name: 'game-over', params: { gameId : gameId}});
        }
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
ul, li {
    list-style-type: none;
    list-style-position:inside;
    margin:0;
    padding:0;
}

.hostImage {
    padding-left: 10px;
    width: 20px;
    height: auto;
}
</style>