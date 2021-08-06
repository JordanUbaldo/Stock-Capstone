<template>
  <div>
    <h3>Stock</h3>
    <form v-on:submit.prevent="getStockDetails">
      <input type="text" v-model="stockTicker">
      <button type="submit">Get Stock</button>
    </form>
    <article>
      <h2>
        {{stockSummary.symbol}}
        {{stockSummary.companyName}}
      </h2>
      <table v-show="stockSummary !== ''">
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
    <trade-form v-bind:stockName="stockSummary.companyName" v-bind:symbol="stockSummary.symbol" v-bind:pricePerShare="stockSummary.latestPrice" v-if="stockSummary.symbol && stockSummary.companyName"></trade-form>
  </div>
</template>

<script>
import stockService from '../services/StockService';
import tradeForm from '@/components/TradeForm'

export default {
    name: 'stock',
    data() {
      return {
        stockTicker: "",
        stockSummary: "",
        isSearchSuccessful: false
      }
    },
    components: {
      tradeForm
    },
    methods: {
        getStockDetails() {
            stockService.getStockExternal(this.stockTicker).then(response => {
                this.stockSummary = response.data;
            });
            this.isSearchSuccessful = true;
        }
    },
    computed: {
    }
}
</script>

<style>

</style>