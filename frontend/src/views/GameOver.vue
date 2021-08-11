<template>
<div>
    <div id="gameover-view">
        <div id="gameover-summary" class="main-div">
            <game-summary />
        </div>
        <div id="gameover-leaderboard" class="main-div">
            <leader-board />
        </div>
    </div>
</div>
</template>

<script>
import GameSummary from "@/components/games/GameSummary"
import LeaderBoard from "@/components/games/LeaderBoard"
export default {
    components: {
        GameSummary,
        LeaderBoard
    }
}
</script>

<style>
    #gameover-summary {
        grid-area: gameover-summary;
    }

    #gameover-leaderboard {
        grid-area: gameover-leaderboard;
    }

    #gameover-view {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-areas:
            "gameover-summary"
            "gameover-leaderboard";
    }
</style>