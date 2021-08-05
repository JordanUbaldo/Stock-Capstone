<template>
  <div>
      <div id="logged-out" v-if="!$store.state.token">
        <login />
        <register />
      </div>
      <div id="logged-in" v-else>
        <logout-btn />
        <create-game-btn />
        <game-list />
        <invite-list />
      </div>
  </div>
</template>

<script>
import GameList from '@/components/GameList.vue';
import InviteList from '@/components/InviteList.vue';
import Login from '@/components/Login.vue';
import Register from '@/components/Register.vue';
import CreateGameBtn from '@/components/CreateGameBtn.vue';
import LogoutBtn from '@/components/LogoutBtn.vue';

export default {
    name: "TheSidebar",
    data() {
      return {
      }  
    },
    components: {
        GameList,
        InviteList,
        Login,
        Register,
        CreateGameBtn,
        LogoutBtn
    }
};
</script>

<style>

</style>