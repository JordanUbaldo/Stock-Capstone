<template>
  <ul>
      <li v-for="game in games" v-bind:key="game.id">{{ game.name }} <img v-if="host" src="assets/Crown.png" alt=""></li>
  </ul>
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

</style>