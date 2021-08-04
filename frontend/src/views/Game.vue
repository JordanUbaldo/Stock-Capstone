<template>
  <div id="gameContainer">
    <game-summary id="summary" />
    <game-details id="details"></game-details>
    <leader-board />
  </div>
</template>

<script>
import GameDetails from "@/components/GameDetails"
import GameSummary from "@/components/GameSummary"
import Leaderboard from "@/components/LeaderBoard"

export default {
    name: "game",
    components: {
        GameDetails,
        GameSummary,
        Leaderboard
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
        grid-template-areas:
            "summary summary"
            "details leaderboard"
    }

    summary {
        grid-area: summary
    }

    details {
        grid-area: details
    }

    leaderboard {
        grid-area: lea
    }

</style>