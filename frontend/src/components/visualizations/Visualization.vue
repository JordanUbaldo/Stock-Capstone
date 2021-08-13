<template>
  <div>
    <p>This should show the chart</p>
    <button v-on:click="getPortfolioHistory">View Portfolio History</button>
    <balance-over-time id=balanceChart v-if="isDataLoaded" v-bind:chartdata="balanceData"/>
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
            // balanceHistory.forEach(element => console.log(element));
            
            let balances = balanceHistory.map(element => {
                return element.amount;
            })

            let times = balanceHistory.map(element => {
                return element.timestamp;
            })

            times.forEach(element => {
                console.log(element);
            });

            this.balanceData = {
                labels: times,
                datasets: [
                    {
                        label: "Balance",
                        data: balances,
                        borderWidth: 1,
                        tension: 0
                    }
                ]
            }

            this.isDataLoaded = true;
        }
    }
}
</script>

<style>
    #balanceChart {
        height: 10vh;
        width: 10vh;
    }
</style>