<template>
  <div>
      Game Invites
      <ul>
          <li v-for="invite in invited" v-bind:key="invite.gameId">
            {{invite.gameName}}
            <button>Accept</button>
            <button>Decline</button>
          </li>
      </ul>
  </div>
</template>

<script>
//import gamesService from '@/services/GamesService.js';

export default {
    name: "invite-list",
    data() {
        return {
            invites: []
        }
    },
    computed: {
        invited() {
            return this.$store.state.games.filter(game => game.playerStatus == 'Pending');
        }
    }
}
</script>

<style>

</style>