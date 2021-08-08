<template>
  <div v-if="$route.name.match('game-over')">
      <h1>Game Over</h1>
      <h2>Winner!</h2>
      <p>{{ winner.username }} {{ winner.amount }}</p>
    </div>
</template>

<script>
export default {
    props: ["winner"]
}
</script>

<style>

</style>