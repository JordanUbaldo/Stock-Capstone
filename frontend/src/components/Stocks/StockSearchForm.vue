<template>
    <form v-on:submit.prevent="getStockDetails">
      <input type="text" v-model="stockTicker">
      <button type="submit">Get Stock</button>
    </form>
</template>

<script>
import stockService from '@/services/StockService';

export default {
    name: 'stock-search-form',
    data() {
        return {
            stockTicker: "",
        }
    },
    methods: {
        async getStockDetails() {
            const stockInfo = (await stockService.getStockExternal(this.stockTicker)).data;
            this.$store.commit("SET_CURRENT_STOCK_DETAILS", stockInfo);
            this.$store.commit('SET_SHOW_FORM');
        }
    }
}
</script>

<style>

</style>