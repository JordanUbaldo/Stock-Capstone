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
import java.sql.Connection;
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
        String sql = "SELECT stock_ticker, stock_name, shares\n" +
                     "FROM stocks\n" +
                     "JOIN users_stocks_games\n" +
                     "ON stocks.stock_id = users_stocks_games.stock_id\n" +
                     "AND game_id = ? AND username = ? AND shares > 0 ORDER BY stock_ticker";
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
        String sql = "SELECT trade_id, game_id, shares, price_per_share, stock_name, stock_ticker, purchase_date, type\n" +
                     "FROM trades\n" +
                     "JOIN trade_type\n" +
                     "ON trade_type.type_id = trades.type_id " +
                     "WHERE game_id = ? and username = ?\n" +
                     "ORDER BY purchase_date;";
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
    public void tradeStocks(TradeRequest trade, String username) throws InsufficientFundsException, InsufficientSharesException, NonExistentStockException {
        String sqlForTrade = "INSERT INTO trades (game_id, username, type_id, stock_ticker, stock_name, amount, purchase_date, price_per_share, shares) " +
                "VALUES (?,?,?,?,?,?,?,?,?);";
        String sqlForGetBalance = "SELECT * FROM balances WHERE game_id = ? AND username = ?;";
        String sqlForUpdateBalance = "UPDATE balances SET amount = ? " +
                "WHERE game_id = ? AND username = ?;";

        String sqlForUpdateStockOfBuy = "UPDATE users_stocks_games\n" +
                                        "SET shares = shares + ?\n" +
                                        "FROM stocks s\n" +
                                        "WHERE s.stock_id = userS_stocks_games.stock_id\n" +
                                        "AND game_id = ? AND username = ? AND stock_ticker = ?;";

        String sqlForUpdateStockOfSell = "UPDATE users_stocks_games\n" +
                                         "SET shares = shares - ?\n" +
                                         "FROM stocks s\n" +
                                         "WHERE s.stock_id = users_stocks_games.stock_id\n" +
                                         "AND game_id = ? AND username = ? AND stock_ticker = ?;";

        String sqlCheckStock = "SELECT game_id, username, stocks.stock_id, shares, stock_ticker, stock_name\n" +
                               "FROM stocks\n" +
                               "JOIN users_stocks_games\n" +
                               "ON stocks.stock_id = users_stocks_games.stock_id\n" +
                               "WHERE game_id = ? AND username = ? AND stock_ticker = ?;";

        String sqlInsertStock = "INSERT INTO stocks (stock_ticker, stock_name)\n" +
                                "VALUES (?,?);";
        String sqlInsertUsersStocksGames = "INSERT INTO users_stocks_games (game_id, username, stock_id, shares)\n" +
                                           "SELECT ?, ?, stock_id, ?\n" +
                                           "FROM stocks\n" +
                                           "WHERE stocks.stock_name = ?;";

        Balance balance = new Balance();
        int typeId = getTypeId(trade.getTradeType());
        BigDecimal amountOfMoney = trade.getPurchasePrice().multiply(new BigDecimal(trade.getNumberOfShares()));
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlForGetBalance, trade.getGameId(), username);
            if (rowSet.next()) {
                balance = mapRowToBalance(rowSet);
            }

            //To check if has enough money to buy stocks
            if (trade.getTradeType().equalsIgnoreCase("Buy")) {

                if (balance.getAmount().subtract(new BigDecimal("19.95")).compareTo(amountOfMoney) >= 0) {
                    BigDecimal newBalance = balance.getAmount().subtract(new BigDecimal("19.95")).subtract(amountOfMoney);
                    jdbcTemplate.update(sqlForTrade, trade.getGameId(), username, typeId, trade.getStockTicker(), trade.getStockName(), amountOfMoney, LocalDate.now(), trade.getPurchasePrice(), trade.getNumberOfShares());
                    jdbcTemplate.update(sqlForUpdateBalance, newBalance, trade.getGameId(), username);
                    //check if this stock exist, if exist then update, if not exist then insert
                    SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlCheckStock, trade.getGameId(), username, trade.getStockTicker());
                    if (rs.next()) {
                        jdbcTemplate.update(sqlForUpdateStockOfBuy, trade.getNumberOfShares(), trade.getGameId(),username, trade.getStockTicker());
                    } else {
                        jdbcTemplate.update(sqlInsertStock, trade.getStockTicker(), trade.getStockName());
                        jdbcTemplate.update(sqlInsertUsersStocksGames, trade.getGameId(), username, trade.getNumberOfShares(), trade.getStockName());
                    }
                } else {
                    throw new InsufficientFundsException();
                }
            }//Sell stocks and update balance amount
            else if (trade.getTradeType().equalsIgnoreCase("Sell")) {
                BigDecimal newBalance = balance.getAmount().subtract(new BigDecimal("19.95")).add(amountOfMoney);
                SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlCheckStock, trade.getGameId(), username, trade.getStockTicker());
                if (rs.next()) {
                    Stock sellStock = mapRowToStockCheck(rs);
                    if (sellStock.getShares() >= trade.getNumberOfShares()) {
                        jdbcTemplate.update(sqlForTrade, trade.getGameId(), username, typeId, trade.getStockTicker(), trade.getStockName(), amountOfMoney, LocalDate.now(), trade.getPurchasePrice(), trade.getNumberOfShares());
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
    public List<TradeRequest> getListOfStocks() {
        List<TradeRequest> result = new ArrayList<>();

        String sql = "SELECT games.game_id, username, stock_ticker, stock_name, shares, 'Sell' as trade_type_name\n" +
                "FROM users_stocks_games\n" +
                "JOIN stocks\n" +
                "ON stocks.stock_id = users_stocks_games\n" +
                "JOIN games\n" +
                "ON games.game_id = users_stocks_games.game_id\n" +
                "WHERE g.end_date IN (SELECT CURRENT_DATE) ORDER BY s.game_id;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while (rowSet.next()) {
                result.add(mapRowToTradeRequest(rowSet));
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
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
        result.setTradeId(rowSet.getInt("trade_id"));
        result.setGameId(rowSet.getInt("game_id"));
        result.setNumberOfShares(rowSet.getInt("shares"));
        result.setPurchasePrice(rowSet.getBigDecimal("price_per_share"));
        result.setStockName(rowSet.getString("stock_name"));
        result.setStockTicker(rowSet.getString("stock_ticker"));
        result.setTradeDate(rowSet.getDate("purchase_date").toString());
        result.setTradeType(rowSet.getString("type"));
        return result;
    }

    private TradeRequest mapRowToTradeRequest(SqlRowSet rowSet) {
        TradeRequest result = new TradeRequest();
        result.setGameId(rowSet.getInt("game_id"));
        result.setUsername(rowSet.getString("username"));
        result.setStockTicker(rowSet.getString("stock_ticker"));
        result.setStockName(rowSet.getString("stock_name"));
        result.setNumberOfShares(rowSet.getInt("shares"));
        result.setTradeType(rowSet.getString("trade_type_name"));
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
