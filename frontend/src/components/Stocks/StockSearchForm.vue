<template>
    <form v-on:submit.prevent="getStockDetails" id="searchForm">
      <input type="text" v-model="stockTicker" placeholder="Ticker Symbol">
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
            try {
            const stockInfo = (await stockService.getStockExternal(this.stockTicker)).data;
            this.$store.commit("SET_CURRENT_STOCK_DETAILS", stockInfo);
            } catch (error) {
                alert(`${error.message}\nSorry, our API is having trouble finding that stock. Try again later.`);
            }
            this.$store.commit('SET_SHOW_FORM_TRUE');
            document.getElementById('searchForm').reset()
        }
    }
}
</script>

<style>

</style>