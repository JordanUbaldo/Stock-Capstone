package com.techelevator.services;


import com.techelevator.dao.GameDao;
import com.techelevator.dao.JdbcGameDao;
import com.techelevator.dao.JdbcTradeDao;
import com.techelevator.dao.TradeDao;
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

    private TradeDao tradeDao;
    private GameDao gameDao;

    public SchedulerService(TradeDao tradeDao, GameDao gameDao) {
        this.tradeDao = tradeDao;
        this.gameDao = gameDao;
    }

    // Checks database every weekday at 4:00 pm US Eastern Standard Time, when the markets close.
    @Scheduled(cron = "0 0 16 * * MON-FRI", zone = "US/Eastern")
    public void tester() throws InsufficientFundsException, InsufficientSharesException, NonExistentStockException {
        List<TradeRequest> stockList =  tradeDao.getListOfStocks();
        for (int i = 0; i < stockList.size(); i++) {
            BigDecimal currentPrice = getLatestPrice(stockList.get(i).getStockTicker());
            stockList.get(i).setPurchasePrice(currentPrice);
            tradeDao.tradeStocks(stockList.get(i), stockList.get(i).getUsername());
            gameDao.changeGameStatusByGameId(stockList.get(i).getGameId());
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void storePortfolioBalancesForActiveGames() {
        // for all gameids
        List<Integer> gameIds = gameDao.getAllActiveGameIds();

        for (Integer id: gameIds) {
            List<Balance> b = gameDao.leaderboard(id);
        }

        System.out.println("Hello");
        System.out.println(gameIds.size());
        for (Integer id: gameIds) {
            System.out.println("Game ID: " + id);
        }
        //    call leaderboard
        //    for all balances
        //
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
