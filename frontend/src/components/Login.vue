<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
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
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import gamesService from "../services/GamesService";
import userService from "../services/UserService";

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
      this.$router.push("/");
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

        if (this.$store.state.allUsers.length === 0) {
          let allUsers = (userService.getUsers(this.$store.state.token)).data.map(element => {
              return element.username;
          });
            
          this.$store.commit('SET_ALL_USERS', allUsers);
          console.log(this.$store.state.allUsers);
        }
    }
  }
};
</script>
