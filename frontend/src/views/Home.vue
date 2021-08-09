<template>
  <div id="home_view">
    <div class="home">
      <h1>Diamond Hands</h1>
      <img id="home-image" src= "../assets/stocks-wall-street.gif" alt="Home Image">
    </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>
#home-image {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
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