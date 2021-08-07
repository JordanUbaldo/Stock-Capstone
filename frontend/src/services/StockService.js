import axios from 'axios';

const key = "/quote?token=Tpk_f2f602e084b44aa5a811ffd1445bc357"
const httpExternal = axios.create({
    baseURL: "https://sandbox.iexapis.com/stable/stock/"
});

const httpInternal = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    getStockExternal(ticker) {
        return httpExternal.get(`/${ticker}${key}`);
    },
    postTrade(trade, token) {
        return httpInternal.post('/games/trades', trade, {headers: {'Authorization': `Bearer ${token}`}})
    },
    getStocks(gameId, token) {
        return httpInternal.get(`/games/${gameId}/stocks`, {headers: {'Authorization': `Bearer ${token}`}})
    },
    getTrades(gameId, token) {
        return httpInternal.get(`/games/${gameId}/trades`, {headers: {'Authorization': `Bearer ${token}`}})
    }
}