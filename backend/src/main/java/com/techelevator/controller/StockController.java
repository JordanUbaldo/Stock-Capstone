package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.TradeDao;
import com.techelevator.exception.InsufficientFundsException;
import com.techelevator.exception.InsufficientSharesException;
import com.techelevator.exception.NonExistentStockException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class StockController {

    private TradeDao tradeDao;
    private GameDao gameDao;

    public StockController(TradeDao tradeDao, GameDao gameDao) {
        this.tradeDao = tradeDao;
        this.gameDao = gameDao;
    }

    @RequestMapping(value = "/games/{gameId}/stocks", method = RequestMethod.GET)
    public List<StockResponse> getStockList(@PathVariable int gameId, Principal principal){
        return tradeDao.getStocksByGameId(gameId, principal);
    }

    @RequestMapping(value = "/games/{gameId}/trades", method = RequestMethod.GET)
    public List<TradeResponse> getTradeList(@PathVariable int gameId, Principal principal){
        return tradeDao.getTradesByGameId(gameId, principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/games/trades", method = RequestMethod.POST)
    public void tradeStocks(@RequestBody TradeRequest trade, Principal principal) throws InsufficientFundsException, InsufficientSharesException, NonExistentStockException {
        tradeDao.tradeStocks(trade, principal);
    }

    @RequestMapping(value = "/games/{gameId}/end", method = RequestMethod.GET)
    public String getWinUserByGameId(@PathVariable int gameId, Principal principal){

        List<Stock> stockList =  tradeDao.getListOfStocks(gameId);
        for (int i = 0; i < stockList.size(); i++) {
            BigDecimal currentPrice = getLatestPrice(stockList.get(i).getStockTicker());
            BigDecimal totalPrice = currentPrice.multiply(new BigDecimal(stockList.get(i).getShares())).subtract(new BigDecimal("19.95"));
            tradeDao.changeBalance(gameId, stockList.get(i).getUsername(),totalPrice);
        }
        Balance maxBalance = tradeDao.findMaxAmountByGameId(gameId);
        gameDao.changeGameStatusByGameId(gameId);
        return maxBalance.getUserName();
    }

    private BigDecimal getLatestPrice(String stockTicker) {
        RestTemplate restTemplate = new RestTemplate();
        LinkedHashMap stockMap = (LinkedHashMap) restTemplate.getForObject("https://sandbox.iexapis.com/stable/stock/"+stockTicker+"/quote?token=Tpk_f2f602e084b44aa5a811ffd1445bc357", Object.class);
        BigDecimal price = new BigDecimal(stockMap.get("latestPrice").toString());
        return price;
    }


}
