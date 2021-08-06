<template>
  <div>
      <!-- Game Name -->
      <p>{{ $store.state.currentGameName }}</p>
      <!-- End Date -->
      <p>End Date</p>
      <!-- Invite Button for Host -->
      <router-link v-bind:to="{ name:'invite', params: { gameId : $store.state.currentGameId}}"><button type="button">Invite</button></router-link>
  </div>
</template>

<script>
export default {
    name: "game-summary",

}
</script>

<style>

</style>