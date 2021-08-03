<template>
<div>
  <ul>
      <li v-for="game in games" v-bind:key="game.id">{{ game.gameName }} <img class="hostImage" v-show="game.host == $store.state.user.username" src="../assets/Crown.png" alt="Host Image"></li>
  </ul>
  </div>
</template>

<script>
<<<<<<< HEAD
//import gamesService from "@/services/GamesService.js";
=======
// import gamesService from "@/services/GamesService.js";
>>>>>>> 6e47035e6d6566525c95f98ed534440a73c6b21e

export default {
    name: "game-list",
    data() {
        return {
<<<<<<< HEAD
            games: [],
            host : false
=======
>>>>>>> 6e47035e6d6566525c95f98ed534440a73c6b21e
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
<<<<<<< HEAD
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
=======
    // created() {
    //     gamesService
    //         .getGames()
    //         .then(response => {
    //             if(response.status === 200) {
    //                 this.$store.commit("SET_GAMES", response.data);
    //             }
    //         })
    // }
>>>>>>> 6e47035e6d6566525c95f98ed534440a73c6b21e
}
</script>

<style>

.hostImage {
    width: 20px;
    height: auto;
}
</style>