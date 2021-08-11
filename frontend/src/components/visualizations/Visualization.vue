<template>
  <div>
    <p>This should show the chart</p>
    <button v-on:click="getPortfolioHistory">View Portfolio History</button>
    <balance-over-time />
  </div>
</template>

<script>
import BalanceOverTime from "@/components/visualizations/BalanceOverTime"
import gamesService from '@/services/GamesService'

export default {
    name: "visualizations",
    components: {
        BalanceOverTime
    },
    computed: {
        currentGameId() {
            return this.$store.state.currentGameId;
        }
    },
    methods: {
        async getPortfolioHistory() {
            let response = await gamesService.getPortfolioValueHistory(this.currentGameId, this.$store.state.token);
            let balanceHistory = response.data;
            console.log(balanceHistory);
            
            balanceHistory.forEach(element => {
                  console.log(element);
            });
        }
    }
}
</script>

<style>

</style>