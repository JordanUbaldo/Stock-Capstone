<template>
  <div>
    <stock-list />
    <br>
    <stock-search-form />
    <stock-details v-if="$store.state.showForm" />
    <trade-form v-if="$store.state.showForm" />
    <button class="btn" v-on:click="flipViewTradeHistory">{{ buttonMessage }}</button>
    <trade-history v-if="viewTradeHistory" />
  </div>
</template>

<script>
import StockList from '@/components/stocks/StockList'
import TradeForm from '@/components/stocks/TradeForm'
import StockSearchForm from '@/components/stocks/StockSearchForm'
import StockDetails from '@/components/stocks/StockDetails'
import TradeHistory from '@/components/stocks/TradeHistory'

export default {
  name: 'stock-parent',
  data() {
    return {
      viewTradeHistory: false,
      buttonMessage: "Show Trade History"
    }
  },
  methods: {
    flipViewTradeHistory() {
      this.viewTradeHistory = !this.viewTradeHistory;

      if (this.viewTradeHistory) {
        this.buttonMessage = "Hide Trade History";
      } else {
        this.buttonMessage = "Show Trade History";
      }
    }
  } , 
  components: { 
    StockList,
    TradeForm,
    StockDetails,
    StockSearchForm,
    TradeHistory
  }
}
</script>

<style>

</style>