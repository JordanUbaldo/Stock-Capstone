<template>
  <div>
    <article v-show="$store.state.currentStockDetails !== {}">
      <h2>
        {{ currentStockDetails.symbol }}
        <br>
        {{ currentStockDetails.companyName }}
      </h2>
      <table>
        <tr>
          <td>Latest Price</td>
          <td>{{ currencyFormatter.format(currentStockDetails.latestPrice) }}</td>
        </tr>
        <tr>
          <td>Latest Update</td>
          <td>{{ currentStockDetails.latestTime }}</td>
        </tr>
        <tr>
          <td>Previous Close</td>
          <td>{{ currencyFormatter.format(currentStockDetails.previousClose) }}</td>
        </tr>
        <tr>
          <td>Change</td>
          <td>{{ currentStockDetails.change }}</td>
        </tr>
        <tr>
          <td>52 Week High</td>
          <td>{{ currentStockDetails.week52High }}</td>
        </tr>
        <tr>
          <td>52 Week Low</td>
          <td>{{ currentStockDetails.week52Low }}</td>
        </tr>
      </table>
    </article>
  </div>
</template>

<script>

export default {
    name: 'stock-details',
    data() {
      return {
          currencyFormatter: new Intl.NumberFormat('en-US', {
              style: 'currency',
              currency: 'USD',
              maximumFractionDigits: 2
          })
      }
    },
    computed: {
      currentStockDetails() {
        return this.$store.state.currentStockDetails;
      }
    }

}
</script>

<style>

</style>