<template>
  <div>
      <button class="sb-btn" v-on:click="logout">Logout</button>
  </div>
</template>

<script>
export default {
    name: "logout-btn",
    methods: {
        logout() {
            this.$router.push("/logout");
        }
    }

}
</script>

<style>

</style>