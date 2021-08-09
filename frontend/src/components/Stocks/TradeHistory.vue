<template>
  <div>
      <table>
          <tr>
              <td>Transaction Date</td>
              <td>Ticker Symbol</td>
              <td>Stock Name</td>
              <td>Transaction Type</td>
              <td>Price per Share</td>
              <td>Number of Shares</td>
          </tr>
          <tr v-for="trade in trades" v-bind:key="trade.tradeId" id="data">
              <td>{{ trade.tradeDate }}</td>
              <td>{{ trade.stockTicker }}</td>
              <td>{{ trade.stockName }}</td>
              <td>{{ trade.tradeType }}</td>
              <td>{{ currencyFormatter.format(trade.purchasePrice) }}</td>
              <td>{{ trade.numberOfShares }}</td>
          </tr>
      </table>
  </div>
</template>

<script>
import stockService from '@/services/StockService';

export default {
    name: 'trade-history',
    data() {
        return {
            tradeId: 0,
            trades: [],
            currencyFormatter: new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD',
                maximumFractionDigits: 2
          })
        }
    },
    computed: {
        currentGameId() {
            return this.$store.state.currentGameId;
        }

    },
    async created() {
         let tradeData = (await stockService.getTrades(this.currentGameId, this.$store.state.token)).data;
         this.trades = tradeData.sort()
    }
}
</script>

<style>

</style>