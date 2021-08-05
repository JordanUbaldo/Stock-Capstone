package com.techelevator.model;

import java.math.BigDecimal;

public class Balance {

    private int balanceId;
    private int gameId;
    private String userName;
    private BigDecimal amount = new BigDecimal(100000);

    public Balance() {

    }


    //Constructor for putting into DB after received from FE
    public Balance(int gameId, String userName) {
        this.gameId = gameId;
        this.userName = userName;
    }

    //Constructor for pulling information from DB
    public Balance(int balanceId, int gameId, String userName, BigDecimal amount) {
        this.balanceId = balanceId;
        this.gameId = gameId;
        this.userName = userName;
        this.amount = amount;
    }

    //Space for methods


    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
