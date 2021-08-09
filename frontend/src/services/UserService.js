import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    getUsers(token) {
        return http.get('/users',{headers: {'Authorization': `Bearer ${token}`}});
    },
    getUsersForGame(gameId,token) {
        return http.get(`/games/${gameId}/users`,{headers: {'Authorization': `Bearer ${token}`}});
    },
    getBalancesForGame(gameId, token) {
        return http.get(`/games/${gameId}/balance`,{headers: {'Authorization': `Bearer ${token}`}});
    }
}