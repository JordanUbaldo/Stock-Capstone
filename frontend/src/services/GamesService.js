/*
ViewGames
CreateGame
InvitePlayers
PlayerResponse
*/

import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    getGames() {
        return http.get('/games');
    }
}