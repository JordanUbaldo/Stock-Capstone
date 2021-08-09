<template>
<div>
    <h2>Game List</h2>
  <ul>
     <li class="game-list" v-for="game in games" v-bind:key="game.gameId" @click="routeToGame(game.gameId, game.gameName, game.endDate)">{{ game.gameName }}<img class="hostImage" v-show="game.host == $store.state.user.username" src="@/assets/Crown.png" alt="Host Image"></li>
  </ul>
  </div>
</template>

<script>
import stockService from "@/services/StockService";
import userService from '@/services/UserService';
import gamesService from "@/services/GamesService";

export default {
    name: "game-list",
    data() {
        return {
            gameList: []
       };
    },
    computed: {
        games() {
            return this.$store.state.acceptedGames.filter(game => {
                return !this.isGameOver(game.endDate);
            })
        },
    },
    methods: {
        async routeToGame(gameId, gameName, gameEndDate){
            if(this.$route.path != `/game/${gameId}`) {

                this.$store.commit("SET_CURRENT_GAME_ID", gameId);
                this.$store.commit("SET_CURRENT_GAME_NAME", gameName);
                this.$store.commit('SET_CURRENT_GAME_END_DATE', gameEndDate);

                const response = await gamesService.getLeaderboard(this.$store.state.currentGameId, this.$store.state.token);
                const leaderboard= response.data.sort((a,b) => b.amount - a.amount)
                this.$store.commit('SET_CURRENT_LEADERBOARD', leaderboard);

                const rawStocksResponse = await stockService.getStocks(gameId, this.$store.state.token);
                this.$store.commit('SET_CURRENT_USER_STOCKS', rawStocksResponse.data);

                const rawBalanceResponse = await userService.getBalancesForGame(gameId, this.$store.state.token);
                this.$store.commit('SET_CURRENT_BALANCES', rawBalanceResponse.data);
                
                this.$store.commit('CLEAR_CURRENT_STOCK_DETAILS');
                this.$store.commit('SET_SHOW_FORM_FALSE');
                this.$router.push({ name: 'game', params: { gameId : gameId}});
            }
        },
        isGameOver(endDate) {
            const marketCloseTime = "T16:00:00";
            const end = new Date(endDate + marketCloseTime);

            return Date.now() - end.getTime()> 0;
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
</style>