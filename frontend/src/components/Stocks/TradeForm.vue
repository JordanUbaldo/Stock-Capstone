<template>
  <div id="content">
      <form v-on:submit.prevent="postTrade" id="tradeForm">
          <br>
          <div class="trade">
            <label for="tradeType">Buy/Sell: </label>
            <select name="tradeType" id="tradeType" v-model="tradeType">
                <option value="Buy">Buy</option>
                <option value="Sell">Sell</option>
            </select>
          </div>
          <br>
          <div class="trade">
              <label for="numberOfShares">Number of Shares: </label>
              <input type="text" id="numberOfShares" v-model="numberOfShares" v-on:change="setTotalCost">
          </div>
          <br>
          <div class="trade">
              <label for="totalCost">Total Cost: </label>
              <input type="text" id="totalCost" v-model="totalCost" v-on:change="setNumberOfShares">
          </div>
          <br>
          <br>
          <button type="submit">Submit Trade</button>
          <button type="reset" v-on:click="cancel">Cancel</button>
      </form>
  </div>
</template>

<script>
import stockService from '@/services/StockService';

export default {
    name: "trade-form",
    props: ['gameId'],
    data() {
        return {
            tradeType: "",
            numberOfShares: 0,
            totalCost: 0,
            show: true
        }
    },
    computed: {
        currentStock() {
            return this.$store.state.currentStockDetails;
        },
        currentGameId() {
            return this.$store.state.currentGameId;
        }
    },
    methods: {
        setNumberOfShares() {
            this.numberOfShares = Math.floor(this.totalCost / this.currentStock.latestPrice);
            this.numberOfShares = parseInt(this.numberOfShares);
            this.totalCost = this.totalCost - (this.totalCost % this.currentStock.latestPrice);
        },
        setTotalCost() {
            this.totalCost = this.numberOfShares * this.currentStock.latestPrice;
        },
        async postTrade() {
            const trade = {
                gameId: this.currentGameId,
                stockTicker: this.currentStock.symbol,
                stockName: this.currentStock.companyName,
                tradeType: this.tradeType,
                numberOfShares: this.numberOfShares,
                amountOfMoney: this.totalCost,
                purchasePrice: this.currentStock.latestPrice
            }
            // Handle different response codes
            try {
                await stockService.postTrade(trade, this.$store.state.token);
            } catch (error) {
                alert(`Error: ${error.response.data.status}\n${error.response.data.message}`);
            }

            const stockList = await stockService.getStocks(this.currentGameId, this.$store.state.token);
            this.$store.commit('SET_CURRENT_USER_STOCKS', stockList.data);
            this.$store.commit('CLEAR_CURRENT_STOCK_DETAILS');
            document.getElementById('tradeForm').reset();
            this.$store.commit('SET_SHOW_FORM_FALSE');
        },
        cancel() {
            this.$store.commit('CLEAR_CURRENT_STOCK_DETAILS');
            this.$store.commit('SET_SHOW_FORM_FALSE');
        }
    }
    
}
</script>

<style>
    .trade {
        display: block;
    }

</style>