<template>
  <div>
      <div id="logged-out" v-if="!$store.state.token">
        <button v-show="!$store.state.loginFormState" @click="loginBtn" >Login</button>
        <button v-show="!$store.state.registerFormState" @click="registerBtn" >Register</button>
        <div v-show="$store.state.loginFormState">
        <login />
        </div>
        <div v-show="$store.state.registerFormState">
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
import GameList from '@/components/sidebar/GameList.vue';
import InviteList from '@/components/sidebar/InviteList.vue';
import Login from '@/components/sidebar/Login.vue';
import Register from '@/components/sidebar/Register.vue';
import CreateGameBtn from '@/components/sidebar/CreateGameBtn.vue';
import LogoutBtn from '@/components/sidebar/LogoutBtn.vue';

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
    },
    methods: {
      loginBtn(){
        if(this.$route.path != '/'){
        this.$router.push("/");
        }
        this.$store.commit("LOGIN_BTN");
      },
      registerBtn(){
        if(this.$route.path != '/'){
        this.$router.push("/");
        }
        this.$store.commit("REGISTER_BTN");
      }
    }
};
</script>

<style>

</style>