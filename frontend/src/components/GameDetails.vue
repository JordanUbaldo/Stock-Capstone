<template>
  <div>
      <!-- User Balance -->
      <p>Balance</p>
      <!-- List of User's Stocks, Trade Button per Stock -->
      <stock-list></stock-list>
      <!-- Trade Stocks Button -->
      <p>Trade Button</p>
      <!-- Form to type name of stock to trade, auto-populate with user stock if selected above -->
      <p>Trade Form</p>
  </div>
</template>

<script>
import StockList from "@/components/StockList.vue"

export default {
  name: "game-details",
  components: {
    StockList
  }

}
</script>

<style>

</style>