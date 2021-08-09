<template>
  <div id="container">
      <h2>Your Stocks:</h2>
      <table>
          <tr id="tableHead">
              <td>Ticker Symbol</td>
              <td>Stock Name</td>
              <td>Number of Shares</td>
          </tr>
          <tr v-for="stock in ownedStocks" v-bind:key="stock.stockTicker" v-on:click="getStockDetails(stock.stockTicker)" class="stockSummary">
              <td>{{ stock.stockTicker }}</td>
              <td>{{ stock.stockName }}</td>
              <td>{{ stock.numberOfShares }}</td>
          </tr>
      </table>
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
            try {
                const stockInfo = (await stockService.getStockExternal(stockTicker)).data;
                this.$store.commit("SET_CURRENT_STOCK_DETAILS", stockInfo);
                this.$store.commit("SET_SHOW_FORM_TRUE");
            } catch (error) {
                alert(`${error.message}\nSorry, our API is having trouble finding that stock. Try again later.`);
            }
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

    table {
        border: 1px solid gold;
        /* border-collapse: collapse; */
    }
</style>