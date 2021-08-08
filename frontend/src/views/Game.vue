<template>
  <div id="router_view">
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
import GameDetails from "@/components/games/GameDetails"
import GameSummary from "@/components/games/GameSummary"
import LeaderBoard from "@/components/games/LeaderBoard"
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
    },
    created() {
        
    }
}
</script>

<style>
    #summary {
        grid-area: summary;
        border: 1px solid black;
    }

    #details {
        grid-area: details;
        border: 1px solid black;
    }

    #leaderBoard {
        grid-area: leaderboard;
    }
    #router_view {
        display: grid;
        grid-template-columns: 2fr 1fr 1fr;
        grid-template-areas:
            "summary summary summary"
            "details details leaderboard"
            "details details leaderboard"
            "details details leaderboard"
            "details details leaderboard";
    }


</style>