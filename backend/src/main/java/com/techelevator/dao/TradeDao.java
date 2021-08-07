package com.techelevator.dao;

import com.techelevator.exception.InsufficientFundsException;
import com.techelevator.exception.InsufficientSharesException;
import com.techelevator.exception.NonExistentStockException;
import com.techelevator.model.*;

import java.security.Principal;
import java.util.List;

public interface TradeDao {
    List<StockResponse> getStocksByGameId(int gameId, Principal principal);
    List<TradeResponse> getTradesByGameId(int gameId, Principal principal);
    void tradeStocks(TradeRequest trade, Principal principal) throws InsufficientFundsException, InsufficientSharesException, NonExistentStockException;
    User getWinUserByGameId(int gameId, Principal principal);
}
