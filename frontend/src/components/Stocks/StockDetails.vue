<template>
  <div>
    <h3>Stock</h3>
    <form v-on:submit.prevent="getStockDetails">
      <input type="text" v-model="stockTicker">
      <button type="submit">Get Stock</button>
    </form>
    <article v-on:ev="console.log('test')">
      <h2>
        {{stockSummary.symbol}}
        {{stockSummary.companyName}}
      </h2>
      <table v-show="stockSummary">
        <tr>
          <td>Latest Price</td>
          <td>{{stockSummary.latestPrice}}</td>
        </tr>
        <tr>
          <td>Latest Update</td>
          <td>{{stockSummary.latestTime}}</td>
        </tr>
        <tr>
          <td>Previous Close</td>
          <td>{{stockSummary.previousClose}}</td>
        </tr>
        <tr>
          <td>Change</td>
          <td>{{stockSummary.change}}</td>
        </tr>
        <tr>
          <td>52 Week High</td>
          <td>{{stockSummary.week52High}}</td>
        </tr>
        <tr>
          <td>52 Week Low</td>
          <td>{{stockSummary.week52Low}}</td>
        </tr>
      </table>
    </article>
  </div>
</template>

<script>
import stockService from '@/services/StockService';

export default {
    name: 'stock',
    data() {
      return {
        stockTicker: "",
        stockSummary: "",
        isSearchSuccessful: false,
        showDetailsAndForm: true
      }
    },
    methods: {
        async getStockDetails() {
            const stockInfo = (await stockService.getStockExternal(this.stockTicker)).data;
            this.$store.commit("SET_CURRENT_STOCK_DETAILS", stockInfo);
        }
    }
}
</script>

<style>

</style>