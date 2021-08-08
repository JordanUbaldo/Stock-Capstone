<template>
  <div>
    <div id="balanceDisplay">
    <label for="bal">Your Balance: </label>
    <p id="bal">${{ new Intl.NumberFormat().format(currentUserBalance) }}</p>
    </div>
      Portfolio Value ${{ new Intl.NumberFormat().format($store.state.userPortfolioBalance) }}
  </div>
</template>

<script>

export default {
    name: "balance",
    data() {
      return {
      }
    },
    computed: {
      currentGameId() {
        return this.$store.state.currentGameId;
      },
      currentUserBalance() {
        const balances = this.$store.state.currentBalances;
        let userBalance;
        balances.forEach(b => {
          if (this.$store.state.user.username === b.username) {
            userBalance = b.amount;
          }
        })

        return userBalance;
      }
    }
}
</script>

<style>
  #bal {
    display: inline-block;
  }
</style>