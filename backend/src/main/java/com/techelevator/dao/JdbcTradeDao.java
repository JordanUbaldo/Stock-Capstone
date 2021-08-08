package com.techelevator.dao;

import com.techelevator.exception.InsufficientSharesException;
import com.techelevator.exception.NonExistentStockException;
import com.techelevator.model.*;
import com.techelevator.exception.InsufficientFundsException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class JdbcTradeDao implements TradeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTradeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StockResponse> getStocksByGameId(int gameId, Principal principal) {
        List<StockResponse> result = new ArrayList<>();
        String username = principal.getName();
        String sql = "SELECT stock_ticker, stock_name, shares FROM stocks WHERE game_id = ? AND username = ? ORDER BY stock_ticker";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId, username);
            while (rowSet.next()) {
                result.add(mapRowToStock(rowSet));
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<TradeResponse> getTradesByGameId(int gameId, Principal principal) {
        List<TradeResponse> result = new ArrayList<>();
        String username = principal.getName();
        String sql = "SELECT game_id, shares, price_per_share, stock_name, stock_ticker, purchase_date, typ.type FROM trades AS tra " +
                "JOIN trade_type AS typ ON tra.type_id = typ.type_id " +
                "WHERE game_id = ? and username = ? ORDER BY stock_ticker;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId, username);
            while (rowSet.next()) {
                result.add(mapRowToTrade(rowSet));
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void tradeStocks(TradeRequest trade, Principal principal) throws InsufficientFundsException, InsufficientSharesException, NonExistentStockException {
        String sqlForTrade = "INSERT INTO trades (game_id, username, type_id, stock_ticker, stock_name, amount, purchase_date, price_per_share, shares) " +
                "VALUES (?,?,?,?,?,?,?,?,?);";
        String sqlForGetBalance = "SELECT * FROM balances WHERE game_id = ? AND username = ?;";
        String sqlForUpdateBalance = "UPDATE balances SET amount = ? " +
                "WHERE game_id = ? AND username = ?;";
        String sqlForUpdateStockOfBuy = "UPDATE stocks SET shares = shares + ? WHERE game_id = ? AND username = ? AND stock_ticker = ?;";
        String sqlForUpdateStockOfSell = "UPDATE stocks SET shares = shares - ? WHERE game_id = ? AND username = ? AND stock_ticker = ?;";
        String sqlCheckStock = "SELECT * FROM stocks WHERE game_id = ? AND username = ? AND stock_ticker = ?;";
        String sqlInsertStock = "INSERT INTO stocks (game_id, username, stock_ticker, stock_name, shares) VALUES (?,?,?,?,?);";

        Balance balance = new Balance();
        String username = principal.getName();
        int typeId = getTypeId(trade.getTradeType());
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlForGetBalance, trade.getGameId(), username);
            if (rowSet.next()) {
                balance = mapRowToBalance(rowSet);
            }

            //To check if has enough money to buy stocks
            if (trade.getTradeType().equalsIgnoreCase("Buy")) {
                System.out.println(balance);
                System.out.println(trade.getAmountOfMoney());
                if (balance.getAmount().subtract(new BigDecimal("19.95")).compareTo(trade.getAmountOfMoney()) >= 0) {
                    BigDecimal newBalance = balance.getAmount().subtract(new BigDecimal("19.95")).subtract(trade.getAmountOfMoney());
                    jdbcTemplate.update(sqlForTrade, trade.getGameId(), username, typeId, trade.getStockTicker(), trade.getStockName(), trade.getAmountOfMoney(), LocalDate.now(), trade.getPurchasePrice(), trade.getNumberOfShares());
                    jdbcTemplate.update(sqlForUpdateBalance, newBalance, trade.getGameId(), username);
                    //check if this stock exist, if exist then update, if not exist then insert
                    SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlCheckStock, trade.getGameId(), username, trade.getStockTicker());
                    if (rs.next()) {
                        jdbcTemplate.update(sqlForUpdateStockOfBuy, trade.getNumberOfShares(), trade.getGameId(),username, trade.getStockTicker());
                    } else {
                        jdbcTemplate.update(sqlInsertStock, trade.getGameId(), username, trade.getStockTicker(), trade.getStockName(), trade.getNumberOfShares());
                    }
                } else {
                    throw new InsufficientFundsException();
                }
            }//Sell stocks and update balance amount
            else if (trade.getTradeType().equalsIgnoreCase("Sell")) {
                BigDecimal newBalance = balance.getAmount().subtract(new BigDecimal("19.95")).add(trade.getAmountOfMoney());
                SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlCheckStock, trade.getGameId(), username, trade.getStockTicker());
                if (rs.next()) {
                    Stock sellStock = mapRowToStockCheck(rs);
                    if (sellStock.getShares() >= trade.getNumberOfShares()) {
                        jdbcTemplate.update(sqlForTrade, trade.getGameId(), username, typeId, trade.getStockTicker(), trade.getStockName(), trade.getAmountOfMoney(), LocalDate.now(), trade.getPurchasePrice(), trade.getNumberOfShares());
                        jdbcTemplate.update(sqlForUpdateBalance, newBalance, trade.getGameId(), username);
                        jdbcTemplate.update(sqlForUpdateStockOfSell, trade.getNumberOfShares(), trade.getGameId(),username, trade.getStockTicker());
                    } else {
                        throw new InsufficientSharesException();
                    }
                } else {
                    throw new NonExistentStockException();
                }
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Stock> getListOfStocks(int gameId) {
        List<Stock> result = new ArrayList<>();

        String sql = "SELECT * FROM stocks WHERE game_id = ? ORDER BY stock_ticker";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId);
            while (rowSet.next()) {
                result.add(mapRowToStockCheck(rowSet));
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void changeBalance(int gameId, String username, BigDecimal amount) {
        String sql = "UPDATE balances SET amount = amount + ? WHERE game_id = ? AND username = ?;";
        String sqlForStock = "UPDATE stocks SET shares = 0 WHERE game_id = ? AND username = ?;";
        try {
            jdbcTemplate.update(sql, amount, gameId, username);
            jdbcTemplate.update(sqlForStock, gameId, username);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Balance findMaxAmountByGameId(int gameId) {
        Balance result = new Balance();
        String sql = "SELECT * FROM balances WHERE game_id = ? ORDER BY amount DESC LIMIT 1;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId);
            if (rowSet.next()) {
                result = mapRowToBalance(rowSet);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    private int getTypeId(String tradeType) {
        String sql = "SELECT type_id FROM trade_type WHERE type = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tradeType);
        int result = 0;
        if (rowSet.next()) {
            result = rowSet.getInt("type_id");
        }
        return result;
    }

    private Stock mapRowToStockCheck(SqlRowSet rowSet) {
        Stock result = new Stock();
        result.setStockId(rowSet.getInt("stock_id"));
        result.setGameId(rowSet.getInt("game_id"));
        result.setUsername(rowSet.getString("username"));
        result.setStockTicker(rowSet.getString("stock_ticker"));
        result.setStockName(rowSet.getString("stock_name"));
        result.setShares(rowSet.getInt("shares"));
        return result;
    }

    private Balance mapRowToBalance(SqlRowSet rowSet) {
        Balance result = new Balance();
        result.setBalanceId(rowSet.getInt("balance_id"));
        result.setGameId(rowSet.getInt("game_id"));
        result.setUsername(rowSet.getString("username"));
        result.setAmount(rowSet.getBigDecimal("amount"));
        return result;
    }

    private TradeResponse mapRowToTrade(SqlRowSet rowSet) {
        TradeResponse result = new TradeResponse();
        result.setGameId(rowSet.getInt("game_id"));
        result.setNumberOfShares(rowSet.getInt("shares"));
        result.setPurchasePrice(rowSet.getBigDecimal("price_per_share"));
        result.setStockName(rowSet.getString("stock_name"));
        result.setStockTicker(rowSet.getString("stock_ticker"));
        result.setTradeDate(rowSet.getDate("purchase_date").toString());
        result.setTradeType(rowSet.getString("type"));
        return result;
    }

    private StockResponse mapRowToStock(SqlRowSet rowSet) {
        StockResponse result = new StockResponse();
        result.setStockTicker(rowSet.getString("stock_ticker"));
        result.setStockName(rowSet.getString("stock_name"));
        result.setNumberOfShares(rowSet.getInt("shares"));
        //result.setTotalCost(rowSet.getBigDecimal("amount"));
        return result;
    }

}
