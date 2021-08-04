<template>
  <div>
    <h3>Stock</h3>
    <button v-on:click='getStockDetails("amc")'>Get Stock</button>
  </div>
</template>

<script>
import stockService from '../services/StockService';

export default {
    name: 'stock',
    methods: {
        getStockDetails(stockTicker) {
            stockService.getStock(stockTicker).then(response => {
                console.log(response);
            });
        }
    },
    computed: {
    }
}
</script>

<style>

</style>