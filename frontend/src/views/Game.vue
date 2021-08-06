<template>
  <div id="gameContainer">
    <game-summary id="summary" />
    <game-details id="details" />
    <leader-board id="leaderboard"></leader-board>
    <button v-on:click="viewGames"></button>
  </div>
</template>

<script>
import GameDetails from "@/components/GameDetails"
import GameSummary from "@/components/GameSummary"
import LeaderBoard from "@/components/LeaderBoard"
import gamesService from "@/services/GamesService"

export default {
    name: "game",
    methods: {
        viewGames() {
            gamesService.getGames().then(response => {
                console.log(response);
            })
        }
    },
    components: {
        GameDetails,
        GameSummary,
        LeaderBoard
    }
}
</script>

<style>
    #summary {
        border: 1px solid black;
    }

    #details {
        border: 1px solid black;
    }

    #game-container {
        display: grid;
        grid-template-columns: 1fr 2fr;
        grid-template-areas:
            "summary details"
            "summary details";
    }

    summary {
        grid-area: summary;
    }

    leaderBoard {
        grid-area: leaderboard;
    }

    details {
        grid-area: details;
    }


</style>