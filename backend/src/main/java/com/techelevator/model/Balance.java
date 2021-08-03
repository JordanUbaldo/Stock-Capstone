package com.techelevator.model;

import java.math.BigDecimal;

public class Balance {

    private long balanceId;
    private long gameId;
    private long userId;
    private BigDecimal amount = new BigDecimal(100000);


    //Constructor for putting into DB after received from FE
    public Balance(long gameId, long userId) {
        this.gameId = gameId;
        this.userId = userId;
    }

    //Constructor for pulling information from DB
    public Balance(long balanceId, long gameId, long userId, BigDecimal amount) {
        this.balanceId = balanceId;
        this.gameId = gameId;
        this.userId = userId;
        this.amount = amount;
    }

    //Space for methods




    public long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(long balanceId) {
        this.balanceId = balanceId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
