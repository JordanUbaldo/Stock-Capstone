package com.techelevator.controller;

import com.techelevator.dao.TradeDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class StockController {

    private TradeDao tradeDao;

    public StockController(TradeDao tradeDao) {
        this.tradeDao = tradeDao;
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
    public void tradeStocks(@RequestBody TradeRequest trade, Principal principal){
        tradeDao.tradeStocks(trade, principal);
    }

    @RequestMapping(value = "/games/{gameId}/end", method = RequestMethod.GET)
    public User getWinUserByGameId(@PathVariable int gameId, Principal principal){
        return tradeDao.getWinUserByGameId(gameId, principal);
    }


}
