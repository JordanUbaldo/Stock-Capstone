import axios from 'axios';

const key = "/quote?token=Tpk_f2f602e084b44aa5a811ffd1445bc357"
const httpExternal = axios.create({
    baseURL: "https://sandbox.iexapis.com/stable/stock/"
});

const httpInternal = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getStock(ticker) {
        return httpExternal.get(`/${ticker}${key}`);
    },
    postTrade(trade, token) {
        return httpInternal.post('/games/trades', trade, {headers: {'Authorization': `Bearer ${token}`}})
    }
}