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
import userService from '@/services/UserService.js';

export default {
    name: "create-game",
    data() {
        return {
            game: {
                endDate: "",
                gameName: ""
            },
            gameCreated: false
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

                        userService.getUsersForGame(this.$store.state.currentGameId, this.$store.state.token)
                        .then(response => {
                            if (response.status === 200) {
                                this.$store.commit("SET_CURRENT_GAME_USERS", response.data)
                            }
                        });

                    if (this.$store.state.allUsers.length === 0) {
                        let allUsers = (await userService.getUsers()).data.map(element => {
                            return element.username;
                        });
                        
                        this.$store.commit('SET_ALL_USERS', allUsers);
                    }
                    this.gameCreated = true;
                    alert("Game Created!");
                    this.$router.push({ name: 'invite', params: { gameId : this.$store.state.currentGameId}})
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