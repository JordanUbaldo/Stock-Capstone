<template>
    <div>
        <form class="create-game" v-on:submit.prevent>
            <div class ="field">
                <label for="Name">Name</label>
                <input class="form-control" v-model="game.gameName" type="text">
            </div>
            <div class ="field">
                <label for="endDate">End Date</label>
                <input class="form-control" v-model="game.endDate" type="date">
            </div>
            <button class="btn" type="submit" v-on:click="createGame">Create</button>
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
            const marketCloseTime = "T16:00:00";
            const end = new Date(this.game.endDate + marketCloseTime);
            const endDateHasPassed = Date.now() - end.getTime() >= 0;
            if(!endDateHasPassed) {
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
                    
                    gamesService
                    .getGames(this.$store.state.token, "Accepted")
                    .then(response => {
                        if(response.status === 200) {
                            this.$store.commit("SET_ACCEPTED_GAMES", response.data);
                        }
                    })
                } else if(response.data === 0) {
                    alert("Failed to Create Game");
                }
                }
            })
        } else {
            alert("End date must be after today!");
        }
    }
}
}
</script>

<style>
.create-game {
  width: 100%;
  margin: 0 auto;
}

.create-game label, input {
  display: inline-block;
}

.create-game label {
  width: 15%;
  text-align: right;
}

.create-game label+input {
  width: 40%;
}
</style>