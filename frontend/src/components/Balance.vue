<template>
  <div>
      ${{ new Intl.NumberFormat().format(currentUserBalance) }}
      Portfolio Value
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

</style>