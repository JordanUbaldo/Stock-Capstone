package com.techelevator.model;

import java.math.BigDecimal;

public class TradeRequest {
    private int gameId;
    private String username;
    private String stockTicker;
    private String stockName;
    private String tradeType;
    private int numberOfShares;
    private BigDecimal amountOfMoney;
    private BigDecimal purchasePrice;

    public TradeRequest() {

    }

    public TradeRequest(int gameId, String stockTicker, String stockName, String tradeType, int numberOfShares, BigDecimal amountOfMoney, BigDecimal purchasePrice) {
        this.gameId = gameId;
        this.stockTicker = stockTicker;
        this.stockName = stockName;
        this.tradeType = tradeType;
        this.numberOfShares = numberOfShares;
        this.amountOfMoney = amountOfMoney;
        this.purchasePrice = purchasePrice;
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public BigDecimal getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(BigDecimal amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
