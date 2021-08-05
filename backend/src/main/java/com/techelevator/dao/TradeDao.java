package com.techelevator.dao;

import com.techelevator.model.StockResponse;
import com.techelevator.model.Trade;
import com.techelevator.model.TradeRequest;
import com.techelevator.model.TradeResponse;

import java.security.Principal;
import java.util.List;

public interface TradeDao {
    List<StockResponse> getStocksByGameId(int gameId);
    List<TradeResponse> getTradesByGameId(int gameId);
    void tradeStocks(TradeRequest trade, Principal principal);
}
