package com.techelevator.dao;

import com.techelevator.model.StockResponse;
import com.techelevator.model.Trade;
import com.techelevator.model.TradeResponse;

import java.util.List;

public interface TradeDao {
    List<StockResponse> getStocksByGameId(long gameId);
    List<TradeResponse> getTradesByGameId(long gameId);
    void tradeStock(Trade trade);
}
