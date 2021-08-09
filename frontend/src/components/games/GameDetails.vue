<template>
  <div>
      <!-- User Balance -->
      <balance />
      <!-- List of User's Stocks, Trade Button per Stock -->
      <stock-parent />
  </div>
</template>

<script>
import StockParent from "@/components/stocks/StockParent.vue"
import Balance from "@/components/games/Balance"

export default {
  name: "game-details",
  components: {
    StockParent,
    Balance
  }

}
</script>

<style>

</style>