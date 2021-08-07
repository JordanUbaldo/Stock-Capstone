package com.techelevator.model;

public class Stock {
    private int stockId;
    private int gameId;
    private String username;
    private String stockTicker;
    private String stockName;
    private int shares;

    public Stock() {

    }

    public Stock(int stockId, int gameId, String username, String stockTicker, String stockName, int shares) {
        this.stockId = stockId;
        this.gameId = gameId;
        this.username = username;
        this.stockTicker = stockTicker;
        this.stockName = stockName;
        this.shares = shares;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
