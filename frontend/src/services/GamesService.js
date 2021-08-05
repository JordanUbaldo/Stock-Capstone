import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    getGames(token, status) {
        return http.get(`/games/${status}`,{headers: {'Authorization': `Bearer ${token}`}});
    },
    createGame(game,token){
        return http.post('/games',
        game,{headers: {'Authorization': `Bearer ${token}`}});
    },
    gameInvite(invite){
        return http.post(`/games/${this.$store.state.gameId}/invite`, invite);
    }
}