<template>
  <ul>
      <li v-for="game in this.$store.state.games" v-bind:key="game.id">{{ game.name }} <img v-show="game.host == this.$store.state.user.username" src="assets/Crown.png" alt="Host Image"></li>
  </ul>
</template>

<script>
import gamesService from "@/services/GamesService.js";

export default {
    name: "game-list",
    data() {
        return {
            host : false,
       };
    },
    computed: {

    },
    methods: {
        checkHost(){
            
        }
    },
    created() {
        gamesService
            .getGames()
            .then(response => {
                if(response.status === 200) {
                    this.$store.commit("SET_GAMES", response.data);
                }
            })
    }
}
</script>

<style>

</style>