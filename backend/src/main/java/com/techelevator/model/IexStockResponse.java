package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IexStockResponse {
    private String symbol;
    private String companyName;
    private String latestPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(String latestPrice) {
        this.latestPrice = latestPrice;
    }
}
