<template>
    <div>
        <form v-on:submit.prevent>
            <div class ="field">
                <label for="Name">Name</label>
                <input v-model="game.gameName" type="text">
            </div>
            <div class ="field">
                <label for="endDate">End Date</label>
                <input v-model="game.endDate" type="date">
            </div>
            <button type="submit" v-on:click="createGame">Create Game</button>
        </form>
    </div>
</template>

<script>
import gamesService from "@/services/GamesService.js";
//import userService from '@/services/UserService.js';

export default {
    name: "create-game",
    data() {
        return {
            game: {
                endDate: "",
                gameName: ""
            },
        }
    },
    computed: {
    },
    methods: {
        createGame() {
            gamesService.createGame(this.game,this.$store.state.token).then(async response => {
                if (response.status === 201) {
                    if (response.data > 0) {
                        this.$store.commit("SET_CURRENT_GAME_ID", response.data);
                    alert("Game Created!");
                } else if(response.data === 0) {
                    alert("Failed to Create Game");
                }
                }
            })
        }
    }
}
</script>

<style>

</style>