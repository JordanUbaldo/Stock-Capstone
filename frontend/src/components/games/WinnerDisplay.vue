<template>
  <div v-if="$route.name.match('game-over')">
      <h1>Game Over</h1>
      <h2>Winner!</h2>
      <h3 class="highlight">{{ winner.username }} ${{ new Intl.NumberFormat().format(winner.amount) }}</h3>
    </div>
</template>

<script>
export default {
    props: ["winner"]
}
</script>

<style>

</style>