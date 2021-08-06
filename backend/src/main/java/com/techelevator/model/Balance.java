package com.techelevator.model;

import java.math.BigDecimal;

public class Balance {

    private long balanceId;
    private int gameId;
    private String username;
    private BigDecimal amount = new BigDecimal(100000);

    public Balance() { }
    //Constructor for putting into DB after received from FE
    public Balance(int gameId, String username) {
        this.gameId = gameId;
        this.username = username;
    }

    //Constructor for pulling information from DB
    public Balance(long balanceId, int gameId, String username, BigDecimal amount) {
        this.balanceId = balanceId;
        this.gameId = gameId;
        this.username = username;
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

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
