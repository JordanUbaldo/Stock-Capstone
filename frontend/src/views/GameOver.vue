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
import GameSummary from "@/components/GameSummary"
import LeaderBoard from "@/components/LeaderBoard"
export default {
    components: {
        GameSummary,
        LeaderBoard
    }
}
</script>

<style>

</style>