package com.techelevator.model;

import java.math.BigDecimal;

public class Share {
    private String tickerName;
    private BigDecimal price;
    private int number;

    public Share() { }

    public Share(String tickerName, BigDecimal price) {
        this.tickerName = tickerName;
        this.price = price;
    }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public String getTickerName() { return tickerName; }

    public void setTickerName(String tickerName) { this.tickerName = tickerName; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }
}
