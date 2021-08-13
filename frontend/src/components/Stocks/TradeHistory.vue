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
        },
        trades() {
            return this.$store.state.currentTradeHistory;
        }

    },
    created(){
        const rawTradeHistory = stockService.getTrades(this.currentGameId, this.$store.state.token);
        const tradeHistory = rawTradeHistory.data;
        this.$store.commit('SET_CURRENT_TRADE_HISTORY', tradeHistory); 
    }
}
</script>

<style>

</style>