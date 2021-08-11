<template>
  <div id="home_view">
    <div class="home">
      <h1>Welcome to</h1>
      <img id="home-image" src= "../assets/logo_final_circle.png" alt="Home Image">
      <high-scores />

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
  width: 500px
}

#home_view {
  height: 100vh;
  background-color: #eeeeeece;
  margin: 10px;
  padding: 8px;
  border-radius: 10px;
  white-space: normal;
}
</style>