import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    getGames() {
        return http.get('/games');
    },
    createGame(game){
        return http.post('/games', game);
    },
    gameInvite(invite){
        return http.post(`/games/${this.$store.state.gameId}/invite`, invite)
    }
}