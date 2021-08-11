<template>
  <div>
    <p>This should show the chart</p>
    <button v-on:click="getPortfolioHistory"></button>
    <balance-over-time />
  </div>
</template>

<script>
import BalanceOverTime from "@/components/visualizations/BalanceOverTime"
import gamesService from '@/services/GamesService'

export default {
    components: {
        BalanceOverTime
    },
    computed: {
        currentGameId() {
            return this.$store.state.currentGameId
        }
    },
    methods: {
        async getPortfolioHistory() {
            let balanceHistory = await gamesService.printPortfolioHistory(this.currentGameId, this.$store.state.token);
            balanceHistory.array.forEach(element => {
                  console.log(element);
            });
        }
    }
}
</script>

<style>

</style>