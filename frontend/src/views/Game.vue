<template>
<div>
  <div id="game_view">
    <div id="summary" class="main-div">
    <game-summary />
    </div>
    <div id="details" class="main-div">
    <game-details />
    </div>
    <div id="leaderboard" class="main-div">
    <leader-board />
    </div>
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
    }

    #details {
        grid-area: details;
    }

    #leaderboard {
        grid-area: leaderboard;
    }

    #game_view {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        grid-template-areas:
            "summary summary summary"
            "details details leaderboard"
            "details details leaderboard"
            "details details leaderboard"
            "details details leaderboard";
    }

    @media only screen and (max-width: 1024px) {
    #game_view {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-areas:
            "summary"
            "details"
            "leaderboard";
    }
    }
</style>