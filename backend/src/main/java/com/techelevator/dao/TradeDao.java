package com.techelevator.dao;

import com.techelevator.model.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface TradeDao {
    List<StockResponse> getStocksByGameId(int gameId, Principal principal);
    List<TradeResponse> getTradesByGameId(int gameId, Principal principal);
    void tradeStocks(TradeRequest trade, Principal principal);
    List<Stock> getListOfStocks(int gameId);
    void changeBalance(int gameId, String username, BigDecimal amount);
    Balance findMaxAmountByGameId(int gameId);
}
