<template>
  <div id="home_view">
    <div class="home">
      <high-scores />
      <img id="home-image" src= "../assets/stonks.png" alt="Home Image">

    </div>
  </div>
</template>

<script>
import HighScores from '../components/games/HighScores.vue';
export default {
  name: "home",
  components: {
    HighScores
    },
}
</script>

<style>
#home-image {
  width: 200px
}

#home_view {
  height: 100vh;
  background-color: #eeeeee;
  margin: 10px;
  padding: 8px;
  border-radius: 10px;
  white-space: normal;
}
</style>