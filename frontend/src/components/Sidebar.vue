<template>
  <div>
      <div id="logged-out" v-if="!$store.state.token">
        <button v-show="!loginBtnState" @click="loginBtn" >Login</button>
        <button v-show="!registerBtnState" @click="registerBtn" >Register</button>
        <div v-show="loginBtnState">
        <login />
        </div>
        <div v-show="registerBtnState">
        <register />
        </div>
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
        loginBtnState: true,
        registerBtnState: false
      }  
    },
    components: {
        GameList,
        InviteList,
        Login,
        Register,
        CreateGameBtn,
        LogoutBtn
    },
    methods: {
      loginBtn(){
        this.$router.push("/");
        this.loginBtnState = !this.loginBtnState
        this.registerBtnState = false
      },
      registerBtn(){
        this.$router.push("/");
        this.registerBtnState = !this.registerBtnState
        this.loginBtnState = false
      }
    }
};
</script>

<style>

</style>