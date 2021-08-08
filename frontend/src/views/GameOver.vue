<template>
    <div>
        <div>
            <div>
            <game-summary />
            </div>
            <div>
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

</style>