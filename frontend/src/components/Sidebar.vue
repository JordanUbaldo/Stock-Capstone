<template>
  <div>
      <login />
      <register />
      <create-game-btn />
      <game-list />
      <invite-list />
  </div>
</template>

<script>
import GameList from '@/components/GameList.vue';
import InviteList from '@/components/InviteList.vue';
import Login from '@/components/Login.vue';
import Register from '@/components/Register.vue';
import CreateGameBtn from '@/components/CreateGameBtn.vue';

export default {
    name: "TheSidebar",
    components: {
        GameList,
        InviteList,
        Login,
        Register,
        CreateGameBtn
    }
};
</script>

<style>

</style>