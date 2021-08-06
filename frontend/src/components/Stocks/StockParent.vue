<template>
  <div>
    <stock-list />
    <trade-form />
    <stock-details />
  </div>
</template>

<script>
import StockList from '@/components/StockList'
import TradeForm from '@/components/TradeForm'
import StockDetails from '@/components/StockDetails'


export default {
  name: 'stock-parent',
  components: {
    StockList,
    TradeForm,
    StockDetails
  }
}
</script>

<style>

</style>