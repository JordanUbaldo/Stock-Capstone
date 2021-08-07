<template>
  <div>
    <stock-list />
    <stock-search-form />
    <stock-details v-if="$store.state.currentStockDetails !== {}" />
    <trade-form v-if="$store.state.currentStockDetails !== {}" v-bind:currentGameId="$store.state.currentGameId"/>
  </div>
</template>

<script>
import StockList from '@/components/Stocks/StockList'
import TradeForm from '@/components/Stocks/TradeForm'
import StockSearchForm from './StockSearchForm.vue'
import StockDetails from './StockDetails.vue'


export default {
  name: 'stock-parent',
  components: {
    StockList,
    TradeForm,
    StockDetails,
    StockSearchForm
  }
}
</script>

<style>

</style>