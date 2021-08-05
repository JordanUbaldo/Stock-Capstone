<template>
<div>
    <h3>Game List</h3>
  <ul>
      <li v-for="game in games" v-bind:key="game.Id">{{ game.gameName }} <img class="hostImage" v-show="game.host == $store.state.user.username" src="../assets/Crown.png" alt="Host Image"></li>
  </ul>
  </div>
</template>

<script>    

export default {
    name: "game-list",
    data() {
        return {
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
    },
}
</script>

<style>

.hostImage {
    width: 20px;
    height: auto;
}
</style>