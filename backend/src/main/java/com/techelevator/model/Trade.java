package com.techelevator.model;

import java.math.BigDecimal;

public class Trade {

    private int tradeId;
    private int gameId;
    private String username;
    private String stockTicker;
    private String tradeName;
    private BigDecimal amount;
    private String tradeDate;
    private BigDecimal pricePerShare;
    private int sharesNumber;
    private String tradeType;

    public Trade() {

    }

    public Trade(int gameId, String stockTicker, String tradeName, BigDecimal pricePerShare, int sharesNumber, String tradeType) {
        this.gameId = gameId;
        this.stockTicker = stockTicker;
        this.tradeName = tradeName;
        this.pricePerShare = pricePerShare;
        this.sharesNumber = sharesNumber;
        this.tradeType = tradeType;
    }


    public String getTradeType() { return tradeType; }

    public void setTradeType(String tradeType) { this.tradeType = tradeType; }

    public String getTradeName() { return tradeName; }

    public void setTradeName(String tradeName) { this.tradeName = tradeName; }

    public int getTradeId() { return tradeId; }

    public void setTradeId(int tradeId) { this.tradeId = tradeId; }

    public int getGameId() { return gameId; }

    public void setGameId(int gameId) { this.gameId = gameId; }

    public String getUserId() { return username; }

    public void setUserId(String username) { this.username = username; }

    public String getStockTicker() { return stockTicker; }

    public void setStockTicker(String stockTicker) { this.stockTicker = stockTicker; }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getTradeDate() { return tradeDate; }

    public void setTradeDate(String tradeDate) { this.tradeDate = tradeDate; }

    public BigDecimal getPricePerShare() { return pricePerShare; }

    public void setPricePerShare(BigDecimal pricePerShare) { this.pricePerShare = pricePerShare; }

    public int getSharesNumber() { return sharesNumber; }

    public void setSharesNumber(int sharesNumber) { this.sharesNumber = sharesNumber; }
}
