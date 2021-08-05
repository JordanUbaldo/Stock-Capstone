<template>
  <div id="content">
      <form v-on:submit.prevent>
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
      </form>
  </div>
</template>

<script>
export default {
    name: "trade-form",
    props: ['symbol', 'stockName', 'pricePerShare'],
    data() {
        return {
            trade: {
                gameId: 0,
                tickerSymbol: this.symbol,
                stockName: this.stockName,
                tradeType: "",
                numberOfShares: 0,
                amountOfMoney: 0
            },
            numShares: 0,
            totalCost: 0
        }
    },
    methods: {
        setNumberOfShares() {
            this.numShares = Math.floor(this.totalCost / this.pricePerShare);
            this.trade.numberOfShares = this.numShares;
            this.totalCost = this.totalCost - (this.totalCost % this.pricePerShare);
            this.trade.amountOfMoney = this.totalCost;
        },
        setTotalCost() {
            this.trade.numberOfShares = this.numShares;
            this.totalCost = this.numShares * this.pricePerShare;
            this.trade.amountOfMoney = this.totalCost;
        }
    }
    
}
</script>

<style>
    .trade {
        display: block;
    }

</style>