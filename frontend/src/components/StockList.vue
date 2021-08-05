<template>
  <div id="container">
      <h2>Your Stocks:</h2>
      <ul>
          <li v-for="stock in stocks" v-bind:key="stock.stockTicker" class="stockSummary">
              {{stock.stockTicker}}
              {{stock.stockName}}
              {{stock.numberOfShares}}
              {{stock.totalCost}}
          </li>
      </ul>
      <stock-details />
  </div>
</template>

<script>
import stockService from "../services/StockService.js";
import StockDetails from "@/components/StockDetails";

export default {
    name: "stock-list",
    data() {
        return {
            gameId: 1003,
            stocks: []
        } 
    },
    components: {
        StockDetails
    },
    async created() {
        const rawStocksResponse = await stockService.getStocks(this.gameId, this.$store.state.token);
        this.stocks = rawStocksResponse.data;
    }
}
</script>

<style>
    .stockSummary {
        border: 1px solid gold;
        list-style: none;
    }
</style>