import axios from 'axios';

const key = "/quote?token=Tpk_f2f602e084b44aa5a811ffd1445bc357"
const http = axios.create({
    baseURL: "https://sandbox.iexapis.com/stable/stock/"
});

export default {

    getStock(ticker) {
        return http.get(`/${ticker}${key}`);
    }
}