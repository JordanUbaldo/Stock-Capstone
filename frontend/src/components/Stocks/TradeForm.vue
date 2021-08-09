<template>
  <div id="content">
      <form class="create-trade" v-on:submit.prevent="postTrade" id="tradeForm">
          <br>
          <div class="trade">
            <label for="tradeType">Buy/Sell: </label>
            <select class="form-control" name="tradeType" id="tradeType" v-model="tradeType">
                <option value="Buy" >Buy</option>
                <option value="Sell">Sell</option>
            </select>
          </div>
          <br>
          <div class="trade">
              <label for="numberOfShares">Number of Shares: </label>
              <input class="form-control" type="text" id="numberOfShares" v-model="numberOfShares" v-on:change="setTotalCost">
          </div>
          <br>
          <div class="trade">
              <label for="priceOfStocks">Cost of Stocks: </label>
              <input class="form-control" type="text" id="priceOfStocks" v-model="priceOfStocks" v-on:change="setNumberOfShares">
          </div>
          <div class="trade" v-show="priceOfStocks !== 0">
              <p>
                  Total Cost: {{ commission + priceOfStocks }}
              </p>
          </div>
          <br>
          <br>
          <button class="btn" type="submit">Submit Trade</button>
          <button class="btn" type="reset" v-on:click="cancel">Cancel</button>
          <p>*Markets move fast; the price you see may not be the price the trade is executed at.</p>
          <p>All trades include a $19.95 commission.</p>
      </form>
  </div>
</template>

<script>
import stockService from '@/services/StockService';
import userService from '@/services/UserService';

export default {
    name: "trade-form",
    props: ['gameId'],
    data() {
        return {
            tradeType: "Buy",
            numberOfShares: 0,
            priceOfStocks: 0,
            show: true,
            commission: 19.95
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
            this.numberOfShares = Math.floor(this.priceOfStocks / this.currentStock.latestPrice);
            this.numberOfShares = parseInt(this.numberOfShares);
            this.priceOfStocks = this.priceOfStocks - (this.priceOfStocks % this.currentStock.latestPrice);
        },
        setTotalCost() {
            this.priceOfStocks = this.numberOfShares * this.currentStock.latestPrice;
        },
        async postTrade() {
            const trade = {
                gameId: this.currentGameId,
                stockTicker: this.currentStock.symbol,
                stockName: this.currentStock.companyName,
                tradeType: this.tradeType,
                numberOfShares: parseInt(this.numberOfShares),
                amountOfMoney: this.priceOfStocks,
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

            const rawBalanceResponse = await userService.getBalancesForGame(this.currentGameId, this.$store.state.token);
            this.$store.commit('SET_CURRENT_BALANCES', rawBalanceResponse.data);
            
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
.create-trade {
    width: 100%;
    margin: 0 auto;
}

.create-trade label, input {
    display: inline-block;
}

.create-trade label {
    width: 25%;
    text-align: right;
}

.create-trade label+input {
    width: 20%;
}

#tradeType{
    width: 20%;
}

.trade {
    display: block;
}
</style>