<template>
  
</template>

<script>
import stockService from "../services/StockService.js";

export default {
    name: "stock-list",
    data() {
        return {
            ticker: "amc"
        } 
    },
created() {
        stockService.getStock(this.ticker).then(response => {
            if (response.status === 200) {
                console.log(response.data);
            }
        });
    }
}
</script>

<style>

</style>