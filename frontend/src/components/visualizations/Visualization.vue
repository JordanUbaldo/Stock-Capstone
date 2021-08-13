<template>
  <div>
    <p>This should show the chart</p>
    <button v-on:click="getPortfolioHistory">View Portfolio History</button>
    <balance-over-time v-if="isDataLoaded" v-bind:chartdata="balanceData"/>
  </div>
</template>

<script>
import BalanceOverTime from "@/components/visualizations/BalanceOverTime"
import gamesService from '@/services/GamesService'

export default {
    name: "visualizations",
    data() {
        return {
            isDataLoaded: false,
            balanceData: {},
            options: {
                responsive: true,
                maintainAspectRation: true
            }
        }
    },
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
            let balances = balanceHistory.map(element => {
                return element.amount;
            })

            // let times = balanceHistory.map(element => {
            //     return element.
            // })

            console.log(balances);
            this.balanceData = {
                labels: ['label1', 'label2', 'label3', 'label4'],
                datasets: [
                    {
                        label: "Balance",
                        data: balances,
                        borderWidth: 1
                    }
                ]
            }

        
            
            // balances;
            this.isDataLoaded = true;
        }
    }
}
</script>

<style>

</style>