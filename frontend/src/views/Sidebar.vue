<template>
  <div>
    <router-link to="/" v-show="!hover"><img @mouseover="hover=true" id="logo" src="../assets/logo_final_circle.png"></router-link>
    <router-link to="/" v-show="hover"><img @mouseleave="hover = false" id="logo" src="../assets/logo_hover.png"></router-link>
      <div id="logged-out" v-if="!$store.state.token">
        <div v-show="$store.state.loginFormState">
        <login />
        </div>
        <p v-show="!$store.state.registerFormState">Don't have an account?</p>
        <button class="sb-btn" v-show="!$store.state.registerFormState" @click="registerBtn" >Join Us</button>
        <div v-show="$store.state.registerFormState">
        <register />
        </div>
        <p v-show="!$store.state.loginFormState">Have an account?</p>
        <button class="sb-btn" v-show="!$store.state.loginFormState" @click="loginBtn" >Login</button>
      </div>
      <div id="logged-in" v-else>
        <h1>{{ $store.state.user.username }}</h1>
        <logout-btn />
        <create-game-btn />
        <game-list />
        <invite-list />
        <finished-games />
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
import FinishedGames from '@/components/sidebar/FinishedGames.vue';

export default {
    name: "TheSidebar",
    data() {
      return {
        hover: false
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
      flipImage(){
        if(this.myImage != this.otherImage){
        this.myImage = this.otherImage
        }
      },
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
  width: 150px;
  margin-top: 10px;
}

</style>