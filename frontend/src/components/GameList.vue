<template>
<div>
    <h3>Game List</h3>
  <ul>
     <li v-for="game in games" v-bind:key="game.gameId" @click="routeToGame(game.gameId)">{{ game.gameName }}<img class="hostImage" v-show="game.host == $store.state.user.username" src="../assets/Crown.png" alt="Host Image"></li>
  </ul>
  </div>
</template>

<script>
 import gamesService from "@/services/GamesService.js";

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
        routeToGame(gameId){
            if(this.$route.path != `/game/${gameId}`) {
            this.$store.commit("SET_CURRENT_GAME_ID", gameId);
            this.$router.push({ name: 'game', params: { gameId : gameId}})
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