<template>
  <div>
      <game-list />
      <invite-list />
      <login />
      <register />
      SIDEBAR
  </div>
</template>

<script>
import GameList from '@/components/GameList.vue';
import InviteList from '@/components/InviteList.vue';
import Login from '@/components/Login.vue';
import Register from '@/components/Register.vue';

export default {
    name: "TheSidebar",
    components: {
        GameList,
        InviteList,
        Login,
        Register
    }
};
</script>

<style>

</style>