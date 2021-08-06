<template>
  <div>
      <!-- Game Name -->
      <p>{{ $store.state.currentGameName }}</p>
      <!-- End Date -->
      <p>End Date</p>
      <!-- Invite Button for Host -->
      <button type="submit">Invite</button>
  </div>
</template>

<script>
export default {
    name: "game-summary",

}
</script>

<style>

</style>