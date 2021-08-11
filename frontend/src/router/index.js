import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Game from '../views/Game.vue'
import Logout from '../components/sidebar/Logout.vue'
import Register from '../components/sidebar/Register.vue'
import store from '../store/index'
import CreateGame from '../views/CreateGame.vue'
import Invite from '../views/Invite.vue'
import GameOver from '@/views/GameOver.vue'
import Visualization from '@/components/visualizations/Visualization'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/games/:gameId/visualizations',
      name: 'visuals',
      component: Visualization
    },
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/game/:gameId",
      name: "game",
      component: Game
    },
    {
      path: "/game/:gameId/game-over",
      name: "game-over",
      component: GameOver
    },
    {
      path: "/create",
      name: "create",
      component: CreateGame
    },
    {
    path: "/game/:gameId/invite/",
    name: "invite",
    component: Invite
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/home");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
