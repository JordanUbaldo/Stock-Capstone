<template>
<div>
  <ul>
      <li v-for="game in games" v-bind:key="game.id">{{ game.gameName }} <img class="hostImage" v-show="game.host == $store.state.user.username" src="../assets/Crown.png" alt="Host Image"></li>
  </ul>
  </div>
</template>

<script>
<<<<<<< HEAD
// import gamesService from "@/services/GamesService.js";
=======
//import gamesService from "@/services/GamesService.js";
>>>>>>> a186a108eb57b2b6b41ccb9855ab58e18621e60f

export default {
    name: "game-list",
    data() {
        return {
<<<<<<< HEAD
=======
            games: [],
            host : false
>>>>>>> a186a108eb57b2b6b41ccb9855ab58e18621e60f
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
    // created() {
    //     gamesService
    //         .getGames()
    //         .then(response => {
    //             if(response.status === 200) {
    //                 this.$store.commit("SET_GAMES", response.data);
    //             }
    //         })
    // }
=======
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
>>>>>>> a186a108eb57b2b6b41ccb9855ab58e18621e60f
}
</script>

<style>

.hostImage {
    width: 20px;
    height: auto;
}
</style>