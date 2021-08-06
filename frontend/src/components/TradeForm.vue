<template>
  <div id="content" v-if="show">
      <form v-on:submit.prevent="postTrade">
          <br>
          <div class="trade">
            <label for="tradeType">Buy/Sell: </label>
            <select name="tradeType" id="tradeType" v-model="trade.tradeType">
                <option value="Buy">Buy</option>
                <option value="Sell">Sell</option>
            </select>
          </div>
          <br>
          <div class="trade">
              <label for="numberOfShares">Number of Shares: </label>
              <input type="text" id="numberOfShares" v-model="numShares" v-on:change="setTotalCost">
          </div>
          <br>
          <div class="trade">
              <label for="totalCost">Total Cost: </label>
              <input type="text" id="totalCost" v-model="totalCost" v-on:change="setNumberOfShares">
          </div>
          <br>
          <br>
          <button type="submit">Submit Trade</button>
          <button v-on:click="$emit('hide')">Cancel</button>
      </form>
  </div>
</template>

<script>
import stockService from '@/services/StockService';

export default {
    name: "trade-form",
    props: ['symbol', 'stockName', 'pricePerShare', 'currentGameId'],
    data() {
        return {
            trade: {
                gameId: parseInt(this.currentGameId),
                stockTicker: this.symbol,
                stockName: this.stockName,
                tradeType: "",
                numberOfShares: 0,
                purchasePrice: this.pricePerShare,
                amountOfMoney: 0
            },
            numShares: 0,
            totalCost: 0,
            show: true
        }
    },
    methods: {
        setNumberOfShares() {
            this.numShares = Math.floor(this.totalCost / this.pricePerShare);
            this.trade.numberOfShares = parseInt(this.numShares);
            this.totalCost = this.totalCost - (this.totalCost % this.pricePerShare);
            this.trade.amountOfMoney = this.totalCost;
        },
        setTotalCost() {
            this.trade.numberOfShares = parseInt(this.numShares);
            this.totalCost = this.numShares * this.pricePerShare;
            this.trade.amountOfMoney = this.totalCost;
        },
        postTrade() {
            stockService.postTrade(this.trade, this.$store.state.token);
            this.$store.commit('ADD_STOCK_TO_CURRENT_USER_STOCKS', this.trade);
            this.flipFormVisibility();
        },
        flipFormVisibility() {
            this.show = !this.show;
        }
    }
    
}
</script>

<style>
    .trade {
        display: block;
    }

</style>