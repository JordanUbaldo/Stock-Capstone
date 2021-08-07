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
          <td>{{ currentStockDetails.latestPrice }}</td>
        </tr>
        <tr>
          <td>Latest Update</td>
          <td>{{ currentStockDetails.latestUpdate }}</td>
        </tr>
        <tr>
          <td>Previous Close</td>
          <td>{{ currentStockDetails.previousClose }}</td>
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
      }
    },
    computed: {
      currentStockDetails() {
        return this.$store.state.currentStockDetails;
      },

    },
    created() {
      //consoleStockDetails() {
        console.log(this.currentStockDetails);
      //}
    }

}
</script>

<style>

</style>