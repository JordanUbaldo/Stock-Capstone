<template>
  <div>
      <div id="logged-out" v-if="!$store.state.token">
        <button class="sb-btn" v-show="!$store.state.loginFormState" @click="loginBtn" >Login</button>
        <button class="sb-btn" v-show="!$store.state.registerFormState" @click="registerBtn" >Register</button>
        <div v-show="$store.state.loginFormState">
        <login />
        </div>
        <div v-show="$store.state.registerFormState">
        <register />
        </div>
      </div>
      <div id="logged-in" v-else>
        <h1>{{ $store.state.user.username }}</h1>
        <logout-btn />
        <create-game-btn />
        <game-list />
        <invite-list />
        <finished-games />
        
      </div>
      <router-link to="/"><img id="logo" src= "../assets/stonks.png" alt="Home Image"></router-link>
  </div>
</template>

<script>
import GameList from '@/components/sidebar/GameList.vue';
import InviteList from '@/components/sidebar/InviteList.vue';
import Login from '@/components/sidebar/Login.vue';
import Register from '@/components/sidebar/Register.vue';
import CreateGameBtn from '@/components/sidebar/CreateGameBtn.vue';
import LogoutBtn from '@/components/sidebar/LogoutBtn.vue';
import FinishedGames from '@/components/sidebar/FinishedGames.vue';

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
        LogoutBtn,
        FinishedGames
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
#logo{
  width: 40px
}

</style>