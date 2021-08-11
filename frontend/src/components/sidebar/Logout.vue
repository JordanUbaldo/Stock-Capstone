<template>
<div>
  <div class="main-div">
    <h1>You have been successfully logged out</h1>
  </div>
</div>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
  }
};
</script>
