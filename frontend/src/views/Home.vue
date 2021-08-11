<template>
<div>
  <div class="main-div">
      <h1>Welcome to Super Stonks</h1>
  </div>
  <div id="home_view">
    <div id="introduction" class="main-div">

    </div>
    <div id="top-leaderboard" class="main-div">
      <high-scores />
    </div>
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

  #introduction {
    grid-area: introduction;
  }

  #top-leaderboard {
    grid-area: top-leaderboard;
  }

  #home_view {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas:
      "introduction introduction top-leaderboard";
  }

  @media only screen and (max-width: 1024px) {
  #home_view {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-areas:
      "welcome"
      "introduction"
      "top-leaderboard";
  }
  }
</style>