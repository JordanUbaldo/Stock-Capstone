package com.techelevator.dao;

import com.techelevator.model.*;
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

        String sql = "SELECT stock_ticker, stock_name, shares, amount FROM trades WHERE game_id = ? and username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId, username);

        while (rowSet.next()) {
            result.add(mapRowToStock(rowSet));
        }

        return result;
    }

    @Override
    public List<TradeResponse> getTradesByGameId(int gameId, Principal principal) {
        List<TradeResponse> result = new ArrayList<>();
        String username = principal.getName();
        String sql = "SELECT game_id, shares, price_per_share, stock_name, stock_ticker, purchase_date, typ.type FROM trades AS tra " +
                "JOIN trade_type AS typ ON tra.type_id = typ.type_id " +
                "WHERE game_id = ? and username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId, username);

        while (rowSet.next()) {
            result.add(mapRowToTrade(rowSet));
        }

        return result;
    }

    @Override
    public void tradeStocks(TradeRequest trade, Principal principal) {
        String sqlForTrade = "INSERT INTO trades (game_id, username, type_id, stock_ticker, stock_name, amount, purchase_date, price_per_share, shares)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?);";
        String sqlForGetBalance = "SELECT * FROM balances WHERE game_id = ? AND username = ?;";
        String sqlForUpdateBalance = "UPDATE balances SET amount = ? " +
                "WHERE game_id = ? AND username = ?;";
        Balance balance = new Balance();
        String username = principal.getName();
        int typeId = getTypeId(trade.getTradeType());
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlForGetBalance, trade.getGameId(), username);
        if (rowSet.next()) {
            balance = mapRowToBalance(rowSet);
        }

        //To check if has enough money to buy stocks
        if (trade.getTradeType().equalsIgnoreCase("Buy")) {

            if (balance.getAmount().subtract(new BigDecimal("19.95")).compareTo(trade.getAmountOfMoney()) >= 0) {
                BigDecimal newBalance = balance.getAmount().subtract(new BigDecimal("19.95")).subtract(trade.getAmountOfMoney());
                jdbcTemplate.update(sqlForTrade, trade.getGameId(), username, typeId, trade.getStockTicker(), trade.getStockName(), trade.getAmountOfMoney(), LocalDate.now(), trade.getPurchasePrice(), trade.getNumberOfShares());
                jdbcTemplate.update(sqlForUpdateBalance, newBalance, trade.getGameId(), username);
            } else {
                throw new RuntimeException("Balance is not enough!");
            }
        }

        //Sell stocks and update balance amount
        else if (trade.getTradeType().equalsIgnoreCase("Sell")) {
            BigDecimal newBalance = balance.getAmount().subtract(new BigDecimal("19.95")).add(trade.getAmountOfMoney());
            //BigDecimal newBalance = balance.getAmount().add(trade.getAmountOfMoney());
            jdbcTemplate.update(sqlForTrade, trade.getGameId(), username, typeId, trade.getStockTicker(), trade.getStockName(), trade.getAmountOfMoney(), LocalDate.now(), trade.getPurchasePrice(), trade.getNumberOfShares());
            jdbcTemplate.update(sqlForUpdateBalance, newBalance, trade.getGameId(), username);
        }
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


    private Balance mapRowToBalance(SqlRowSet rowSet) {
        Balance result = new Balance();
        result.setBalanceId(rowSet.getInt("balance_id"));
        result.setGameId(rowSet.getInt("game_id"));
        result.setUserName(rowSet.getString("username"));
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
        result.setTotalCost(rowSet.getBigDecimal("amount"));

        return result;
    }

}
