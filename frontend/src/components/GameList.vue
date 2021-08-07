<template>
<div>
    <h3>Game List</h3>
  <ul>
     <li v-for="game in games" v-bind:key="game.gameId" @click="routeToGame(game.gameId, game.gameName, game.endDate)">{{ game.gameName }}<img class="hostImage" v-show="game.host == $store.state.user.username" src="../assets/Crown.png" alt="Host Image"></li>
  </ul>
  </div>
</template>

<script>
import gamesService from "@/services/GamesService.js";
import stockService from "@/services/StockService";
import userService from '../services/UserService';

export default {
    name: "game-list",
    data() {
        return {
            gameList: []
       };
    },
    computed: {
        games() {
            return this.$store.state.acceptedGames
        }
    },
    methods: {
        viewGames() {
            gamesService.getGames(this.$store.state.token, "Active").then(response => {
                this.gameList = response;
            })
        },
        async routeToGame(gameId, gameName, gameEndDate){
            if(this.$route.path != `/game/${gameId}`) {

                this.$store.commit("SET_CURRENT_GAME_ID", gameId);
                this.$store.commit("SET_CURRENT_GAME_NAME", gameName);
                this.$store.commit('SET_CURRENT_GAME_END_DATE', gameEndDate);
                this.$router.push({ name: 'game', params: { gameId : gameId}});

                const rawStocksResponse = await stockService.getStocks(gameId, this.$store.state.token);
                this.$store.commit('SET_CURRENT_USER_STOCKS', rawStocksResponse.data);

                const rawBalanceResponse = await userService.getBalancesForGame(gameId, this.$store.state.token);
                this.$store.commit('SET_CURRENT_BALANCES', rawBalanceResponse.data);
                
                this.$store.commit('CLEAR_CURRENT_STOCK_DETAILS');
                this.$store.commit('SET_SHOW_FORM_FALSE');
            }
        }
    }
}
</script>

<style>

.hostImage {
    width: 20px;
    height: auto;
}
</style>