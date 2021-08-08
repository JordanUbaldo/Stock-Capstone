<template>
  <div>
    <stock-list />
    <stock-search-form />
    <stock-details v-if="$store.state.showForm" />
    <trade-form v-if="$store.state.showForm" />
  </div>
</template>

<script>
import StockList from '@/components/stocks/StockList'
import TradeForm from '@/components/stocks/TradeForm'
import StockSearchForm from '@/components/stocks/StockSearchForm.vue'
import StockDetails from '@/components/stocks/StockDetails.vue'

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