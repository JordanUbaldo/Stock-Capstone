<template>
<div>
    <h3>Game List</h3>
  <ul>
      <li v-for="game in games" v-bind:key="game.Id">{{ game.gameName }} <img class="hostImage" v-show="game.host == $store.state.user.username" src="../assets/Crown.png" alt="Host Image"></li>
  </ul>
  <button v-on:click="viewGames">view games</button>
  <p>
      {{this.games}}
  </p>
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
            let results = this.$store.state.games.filter((game) => {
                return game.gameStatus == "Active"
            });
            results = results.filter(game => {
                return game.playerStatus == "Accepted";
            });
            return results;
        }
    },
    methods: {
        viewGames() {
            gamesService.getGames(this.$store.state.token).then(response => {
                this.gameList = response;
            })
        }
    },
}
</script>

<style>

.hostImage {
    width: 20px;
    height: auto;
}
</style>