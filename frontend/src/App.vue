<template>
  <div id="app">
    <the-sidebar id=sidebar />
    <router-view id=router_view />
  </div>
</template>
 
<script>
import TheSidebar from "@/components/Sidebar.vue";

export default ({
  components: {
    TheSidebar
  }
})
</script>

<style>
  #app {
    display: grid;
    grid-template-columns: 250px 1fr;
    grid-template-areas:
      "sidebar content"
  }

  sidebar {
    grid-area: sidebar-view;
  }

  content {
    grid-area: router_view;
  }

  #sidebar {
    height: 100vh;
    border: 1px solid red;
  }

  #router_view {
    height: 100vh;
    border: 1px solid black;
  }
</style>