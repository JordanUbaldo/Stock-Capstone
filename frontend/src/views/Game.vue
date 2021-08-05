<template>
  <div id="container">
    <div id="summary">
    <game-summary />
    </div>
    <div id="details">
    <game-details />
    </div>
    <div id="leaderboard">
    <leader-board />
    </div>
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
        #container {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        grid-template-areas:
            "summary summary summary"
            "details details leaderboard"
            "details details leaderboard"
            "details details leaderboard";
    }

    #summary {
        border: 1px solid black;
    }

    #details {
        border: 1px solid black;
    }

    #summary {
        grid-area: summary;
    }

    #leaderBoard {
        grid-area: leaderboard;
    }

    #details {
        grid-area: details;
    }


</style>