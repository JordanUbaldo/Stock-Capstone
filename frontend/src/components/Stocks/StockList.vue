<template>
  <div id="container">
      <h2>Your Stocks:</h2>
      <ul>
          <li v-for="stock in ownedStocks" v-bind:key="stock.stockTicker" class="stockSummary"
          v-on:click="getStockDetails(stock.stockTicker)">
              {{stock.stockTicker}}
              {{stock.stockName}}
              {{stock.numberOfShares}}
              {{stock.totalCost}}
          </li>
      </ul>
  </div>
</template>

<script>
import stockService from "@/services/StockService.js";

export default {
    name: "stock-list",
    data() {
        return {
            currentStockTicker: ""
        } 
    },
    computed: {
        ownedStocks() {
            return this.$store.state.currentUserStocks;
        },
        currentGameId() {
            return this.$store.state.currentGameId
        }
    },
    methods: {
        async getStockDetails(stockTicker) {
            const stockInfo = (await stockService.getStockExternal(stockTicker)).data;
            this.$store.commit("SET_CURRENT_STOCK_DETAILS", stockInfo);
        }
    },
    async created() {
        const rawStocksResponse = await stockService.getStocks(this.currentGameId, this.$store.state.token);
        this.$store.commit('SET_CURRENT_USER_STOCKS', rawStocksResponse.data);
        this.stocks = this.$store.state.currentUserStocks;
    }
}
</script>

<style>
    .stockSummary {
        border: 1px solid gold;
        list-style: none;
    }
</style>