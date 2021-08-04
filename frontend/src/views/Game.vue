<template>
  <div>
    <game-summary />
    <game-details></game-details>
  </div>
</template>

<script>
import GameDetails from "@/components/GameDetails"
import GameSummary from "@/components/GameSummary"

export default {
    name: "game",
    components: {
        GameDetails,
        GameSummary
    }
}
</script>

<style>
    game-summary {
        border: 1px solid black;
    }

    game-details {
        border: 1px solid black;
    }

</style>