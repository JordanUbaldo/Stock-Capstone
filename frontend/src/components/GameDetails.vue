<template>
  <div>
      <!-- User Balance -->
      <balance />
      <!-- List of User's Stocks, Trade Button per Stock -->
      <stock-list></stock-list>
  </div>
</template>

<script>
import StockList from "@/components/StockList.vue"
import Balance from "@/components/Balance"

export default {
  name: "game-details",
  components: {
    StockList,
    Balance
  }

}
</script>

<style>

</style>