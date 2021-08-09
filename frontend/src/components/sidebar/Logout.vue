<template>
<div>
  <div class="main-div">
    <h1>Logout</h1>
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
