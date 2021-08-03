<template>
<div>
  <ul>
      <li v-for="game in games" v-bind:key="game.id">{{ game.gameName }} <img class="hostImage" v-show="game.host == $store.state.user.username" src="../assets/Crown.png" alt="Host Image"></li>
  </ul>
  </div>
</template>

<script>
//import gamesService from "@/services/GamesService.js";

export default {
    name: "game-list",
    data() {
        return {
            games: [],
            host : false
       };
    },
    computed: {
        games() {
            return this.$store.state.games.filter((game) => {
                return game.gameStatus
            });
        }
    },
    methods: {
    },
/*    created() {
        gamesService
            .getGames()
            .then(response => {
                if(response.status === 200) {
                    this.$store.commit("SET_GAMES", response.data);
                }
            })
    }
*/
}
</script>

<style>

.hostImage {
    width: 20px;
    height: auto;
}
</style>