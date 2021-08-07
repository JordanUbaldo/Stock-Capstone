<template>
  <div id="content">
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
          <button type="reset" v-on:click="cancel">Cancel</button>
      </form>
  </div>
</template>

<script>
import stockService from '@/services/StockService';

export default {
    name: "trade-form",
    props: ['currentGameId'],
    data() {
        return {
            trade: {
                gameId: parseInt(this.currentGameId),
                stockTicker: "",
                stockName: "",
                tradeType: "",
                numberOfShares: 0,
                purchasePrice: 0,
                amountOfMoney: 0
            },
            numShares: 0,
            totalCost: 0,
            show: true
        }
    },
    computed: {
        currentStock() {
            return this.$store.state.currentStockDetails;
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
            this.totalCost = this.numShares * this.currentStock.latestPrice;
            this.trade.amountOfMoney = this.totalCost;
        },
        async postTrade() {
            this.trade.stockTicker = this.currentStock.symbol;
            this.trade.stockName = this.currentStock.companyName;
            this.trade.pricePerShare = this.currentStock.latestPrice;
            await stockService.postTrade(this.trade, this.$store.state.token);
            const stockList = await stockService.getStocks(this.trade.gameId, this.$store.state.token);
            this.$store.commit('SET_CURRENT_USER_STOCKS', stockList.data);
            
            console.log(stockList);
            this.$store.commit('CLEAR_CURRENT_STOCK_DETAILS');
        },
        cancel() {
            this.$store.commit('CLEAR_CURRENT_STOCK_DETAILS');
        }
    }
    
}
</script>

<style>
    .trade {
        display: block;
    }

</style>