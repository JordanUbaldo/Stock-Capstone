import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    //Test user Data
    user: currentUser || {username :"testUser1"},
    //Test Game Data
    games: [],
    
    // [{gameName: "testGame1", host: "testUser1", gameStatus: true, playerStatus: "Accepted"},
    // {gameName: "testGame2", host: "testUser2", gameStatus: false, playerStatus: "Accepted"},
    // {gameName: "testGame3", host: "testUser3", gameStatus: true, playerStatus: "Pending"},
    // {gameName: "testGame4", host: "testUser3", gameStatus: true, playerStatus: "Accepted"}],

    currentGameId: 0,
    allUsers: [],
    currentUserStocks: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    SET_ALL_USERS(state, allUsers) {
      state.allUsers = allUsers;
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_GAMES(state, data) {
      state.games = data;
    },
    SET_INVITES(state, data) {
      state.invites = data;
    },
    SET_CURRENT_GAME_ID(state, data) {
      state.currentGameId = data;
    },
    SET_CURRENT_USER_STOCKS(state, data) {
      state.currentUserStocks = data;
    }
  }
})
