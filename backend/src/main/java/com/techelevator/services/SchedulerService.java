package com.techelevator.services;


import com.techelevator.dao.JdbcGameDao;
import com.techelevator.dao.JdbcTradeDao;
import com.techelevator.exception.InsufficientFundsException;
import com.techelevator.exception.InsufficientSharesException;
import com.techelevator.exception.NonExistentStockException;
import com.techelevator.model.Balance;
import com.techelevator.model.IexStockResponse;
import com.techelevator.model.Stock;
import com.techelevator.model.TradeRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;

@Component
public class SchedulerService {

    private JdbcTradeDao jdbcTradeDao;
    private JdbcGameDao jdbcGameDao;

    public SchedulerService(JdbcTradeDao jdbcTradeDao, JdbcGameDao jdbcGameDao) {
        this.jdbcTradeDao = jdbcTradeDao;
        this.jdbcGameDao = jdbcGameDao;
    }

    // Checks database every weekday at 4:00 pm US Eastern Standard Time, when the markets close.
    @Scheduled(cron = "0 21 22 * * MON-FRI", zone = "US/Eastern")
    public void tester() throws InsufficientFundsException, InsufficientSharesException, NonExistentStockException {
        System.out.println("Hello");
        List<TradeRequest> stockList =  jdbcTradeDao.getListOfStocks();
        for (int i = 0; i < stockList.size(); i++) {
            BigDecimal currentPrice = getLatestPrice(stockList.get(i).getStockTicker());
            stockList.get(i).setPurchasePrice(currentPrice);
            jdbcTradeDao.tradeStocks(stockList.get(i), stockList.get(i).getUsername());
            jdbcGameDao.changeGameStatusByGameId(stockList.get(i).getGameId());
        }
    }

    private BigDecimal getLatestPrice(String stockTicker) {
        RestTemplate restTemplate = new RestTemplate();
        IexStockResponse iexStock = new IexStockResponse();
        try {
            iexStock = restTemplate.getForObject("https://sandbox.iexapis.com/stable/stock/"+stockTicker+"/quote?token=Tpk_f2f602e084b44aa5a811ffd1445bc357", IexStockResponse.class);
        } catch (RestClientResponseException e) {
            String.format("%s:%s", e.getRawStatusCode(), e.getStatusText());
        } catch (ResourceAccessException e) {
            e.getMessage();
        }
        BigDecimal price = new BigDecimal(iexStock.getPreviousClose());
        return price;
    }


}
