<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h2>Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button class="sb-btn" type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "@/services/AuthService";
import gamesService from "@/services/GamesService";
import userService from "@/services/UserService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      if(this.$route.path != '/'){
      this.$router.push("/");
      }
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);

        userService
          .getUsers(this.$store.state.token)
          .then(response => {
            if(response.status === 200) {
              this.$store.commit("SET_ALL_USERS", response.data);
            }
          })

        gamesService
            .getGames(this.$store.state.token, "Accepted")
            .then(response => {
                if(response.status === 200) {
                    this.$store.commit("SET_ACCEPTED_GAMES", response.data);
                }
            })

        gamesService
            .getGames(this.$store.state.token, "Pending")
            .then(response => {
                if(response.status === 200) {
                    this.$store.commit("SET_INVITES", response.data);
                }
            })
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });

    }
  }
};
</script>
