<template>
  <div>
        <button v-on:click="getPortfolioHistory">View Portfolio History</button>
      <!-- User Balance -->
      <balance />
      <!-- List of User's Stocks, Trade Button per Stock -->
      <stock-parent />
  </div>
</template>

<script>
import StockParent from "@/components/stocks/StockParent.vue"
import Balance from "@/components/games/Balance"
import gamesService from "@/services/GamesService"

export default {
  name: "game-details",
  components: {
    StockParent,
    Balance
  },
    computed: {
        currentGameId() {
            return this.$store.state.currentGameId;
        }
    },
  methods: {
    async getPortfolioHistory() {
      console.log(this.currentGameId);
        let response = await gamesService.getPortfolioValueHistory(this.currentGameId, this.$store.state.token);
        let balanceHistory = response.data;
        
        balanceHistory.forEach(element => {
              console.log(element);
        });
    }
  }

}
</script>

<style>

</style>