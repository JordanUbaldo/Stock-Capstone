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

let currentAcceptedGames = []
if (localStorage.getItem('acceptedGames') != null) {
JSON.parse(localStorage.getItem('acceptedGames')).forEach(game => {
  currentAcceptedGames.push(JSON.parse(game))
});
}

let currentAllUsers = []
if (localStorage.getItem('allUsers') != null) {
JSON.parse(localStorage.getItem('allUsers')).forEach(user => {
  currentAllUsers.push(JSON.parse(user))
});
}

let currentInvites = []
if (localStorage.getItem('invites') != null) {
JSON.parse(localStorage.getItem('invites')).forEach(invite => {
  currentInvites.push(JSON.parse(invite))
});
}

let userStocks = []
if (localStorage.getItem('currentUserStocks') != null) {
JSON.parse(localStorage.getItem('currentUserStocks')).forEach(stock => {
  currentInvites.push(JSON.parse(stock))
});
}

let gameUsers = []
if (localStorage.getItem('currentGameUsers') != null) {
JSON.parse(localStorage.getItem('currentGameUsers')).forEach(user => {
  currentInvites.push(JSON.parse(user))
});
}

let balances = []
if (localStorage.getItem('currentBalances') != null) {
JSON.parse(localStorage.getItem('currentBalances')).forEach(balance => {
  currentInvites.push(JSON.parse(balance))
});
}

const stockDetails = JSON.parse(localStorage.getItem('currentStockDetails'))
const gameName = localStorage.getItem('gameName');
const gameId = localStorage.getItem('gameId');

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},  
    acceptedGames: currentAcceptedGames || [],
    invites: currentInvites || [],
    currentGameId: gameId || 0,
    currentGameName: gameName || '',
    allUsers: currentAllUsers || [],
    currentUserStocks: userStocks || [],
    currentGameUsers: gameUsers || [],
    currentStockDetails: stockDetails || {},
    currentBalances: balances || [],
    registerFormState: false,
    loginFormState: true,
    showForm: false
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
    SET_ALL_USERS(state, data) {
      state.allUsers = data;
      let storage = [];
      data.forEach(element => {
        let e = JSON.stringify(element);
        storage.push(e);
      });
      localStorage.setItem('allUsers',JSON.stringify(storage))
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
      localStorage.clear
    },
    REGISTER_BTN(state) {
      state.registerFormState = !state.registerFormState;
      state.loginFormState = false;
    },
    LOGIN_BTN(state) {
      state.loginFormState = !state.loginFormState;
      state.registerFormState = false;
    },
    SET_ACCEPTED_GAMES(state, data) {
      state.acceptedGames = data;
      let storage = [];
      data.forEach(element => {
        let e = JSON.stringify(element);
        storage.push(e);
      });
      localStorage.setItem('acceptedGames',JSON.stringify(storage))
    },

    SET_INVITES(state, data) {
      state.invites = data;
      let storage = [];
      data.forEach(element => {
        let e = JSON.stringify(element);
        storage.push(e);
      });
      localStorage.setItem('invites',JSON.stringify(storage))
    },
    SET_CURRENT_GAME_ID(state, data) {
      state.currentGameId = data;
      localStorage.setItem('gameId', data);
    },
    SET_CURRENT_GAME_NAME(state, data) {
      state.currentGameName = data;
      localStorage.setItem('gameName', data);
    },
    SET_CURRENT_USER_STOCKS(state, data) {
      state.currentUserStocks = data;
      let storage = [];
      data.forEach(element => {
        let e = JSON.stringify(element);
        storage.push(e);
      });
      localStorage.setItem('currentUserStocks',JSON.stringify(storage))
    },
    ADD_STOCK_TO_CURRENT_USER_STOCKS(state, data) {
      state.currentUserStocks.push(data);
    },
    SET_CURRENT_GAME_USERS(state, data) {
      state.currentGameUsers = data;
      let storage = [];
      data.forEach(element => {
        let e = JSON.stringify(element);
        storage.push(e);
      });
      localStorage.setItem('currentGameUsers',JSON.stringify(storage))
    },
    ADD_USER_TO_GAME(state, data) {
      state.currentGameUsers.push(data);
    },
    SET_CURRENT_STOCK_DETAILS(state, data) {
      state.currentStockDetails = data;
      localStorage.setItem('currentStockDetails',JSON.stringify(data));
    },
    CLEAR_CURRENT_STOCK_DETAILS(state) {
      state.currentStockDetails = {};
    },
    SET_CURRENT_BALANCES(state, data) {
      state.currentBalances = data;
      let storage = [];
      data.forEach(element => {
        let e = JSON.stringify(element);
        storage.push(e);
      });
      localStorage.setItem('currentBalances',JSON.stringify(storage))
    },
    SET_SHOW_FORM_TRUE(state) {
      state.showForm = true;
    },
    SET_SHOW_FORM_FALSE(state) {
      state.showForm = false;
    }
  }
})
