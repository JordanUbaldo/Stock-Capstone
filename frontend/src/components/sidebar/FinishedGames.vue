<template>
  <div>    
    <h3>Completed Games</h3>
      <ul>
        <li v-for="game in games" v-bind:key="game.gameId" @click="routeToFinishedGameView(game.gameId, game.gameName, game.endDate)">{{ game.gameName }}<img class="hostImage" v-show="game.host == $store.state.user.username" src="@/assets/Crown.png" alt="Host Image"></li>
      </ul>
  </div>
</template>

<script>
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
        this.$store.commit("SET_CURRENT_GAME_ID", gameId);
        this.$store.commit("SET_CURRENT_GAME_NAME", gameName);
        this.$store.commit('SET_CURRENT_GAME_END_DATE', gameEndDate);
        this.$router.push({ name: 'game-over', params: { gameId : gameId}});
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