<template>
  <div id="app">
    <the-sidebar id=sidebar />
    <router-view id=router_view />
  </div>
</template>
 
<script>
import TheSidebar from "@/views/Sidebar.vue";

export default ({
  components: {
    TheSidebar
  }
})
</script>

<style>
  body {
    font-size: 16px;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    background-image: url("../src/assets/wallstreet.jpg");
    background-repeat:no-repeat;
    background-size: cover;
    background-color: #04460e;
    white-space: nowrap;
  }

  #app {
    min-height: 100vh;
    display: grid;
    grid-template-columns: 250px 1fr;
    grid-template-areas:
      "sidebar content";
    text-align: center;
    color: #2e645f;
    margin-top: 60px;
  }

  @media only screen and (max-width: 600px) {
    #app {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-areas:
            "sidebar"
            "content";
    }
    }

  sidebar {
    grid-area: sidebar;
  }

  content {
    grid-area: router_view;
  }

  #sidebar {
    margin: 10px;
    padding: 8px;
    border-radius: 10px;
    white-space: normal;

    background-color:rgba(255, 255, 255, 0.72);
    box-shadow:none;
  }

  #router_view {
    margin: 10px;
    padding: 8px;
    border-radius: 10px;
    white-space: normal;
    background-color:rgba(255, 255, 255, 0.72);
    box-shadow:none;
  }

  .main-div {
    background-color:#2e645f;
    margin: 10px;
    padding: 8px;
    border-radius: 10px;
    white-space: normal;
    color: white;
  }

  .form-control {
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    border: 1px solid #C2C2C2;
    border-radius: 3px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    padding: 10px;
    margin: 5px;
    outline: none;
  }

  .btn {
    margin: 20px 5px 20px 5px;
    padding: 10px 0px 10px 0px;
    width: 150px;
    background-color: #eeeeee;
    border-radius: 5px;
    border: 1px solid #c4c4c4;
    font-size: 16px;
    color:#2e645f;
  }

  .btn:hover {
    background-color: #cccccc;
    cursor: pointer;
  }

  .sb-btn {
    margin: 5px;
    padding: 10px 0px 10px 0px;
    width: 150px;
    background-color: #2e645faf;;
    border-radius: 5px;
    border: 1px solid #033a0c;
    font-size: 16px;
    color: #eeeeee;
  }

  .sb-btn:hover {
    background-color: #022507;
    cursor: pointer;
  }

  .game-list {
    display: inline-block;
    text-align: center;
    width: 70%;
    margin: 5px;
    padding: 10px 0px 10px 0px;
    width: 150px;
    background-color: #2e645f;;
    border-radius: 5px;
    border: 1px solid #033a0c;
    font-size: 16px;
    color: #eeeeee;
  }

  .game-list a {
      color: #eeeeee;
      text-decoration: none;
  }

  .game-list:hover {
      background-color: #022507;
      cursor: pointer;
  }

  .hostImage {
      padding-left: 10px;
      width: auto;
      height: 14px;
  }

  table {
      margin: auto;
      border:none;
      border-collapse: collapse;
  }

  table td {
      padding: 5px 10px 5px 10px;
      border-left: 2px solid #eeeeee;
      border-right: 2px solid #eeeeee;
  }

  table td:first-child {
      border-left: none;
  }

  table td:last-child {
      border-right: none;
  }
</style>