package com.techelevator.dao;

import com.techelevator.model.StockResponse;
import com.techelevator.model.Trade;
import com.techelevator.model.TradeResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcTradeDao implements TradeDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTradeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StockResponse> getStocksByGameId(long gameId) {
        List<StockResponse> result = new ArrayList<>();

        String sql = "SELECT stock_ticker, stock_name, shares, price_per_share * shares as total_cost FROM trades " +
                "WHERE game_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId);

        while (rowSet.next()) {
            result.add(mapRowToStock(rowSet));
        }

        return result;
    }

    @Override
    public List<TradeResponse> getTradesByGameId(long gameId) {
        List<TradeResponse> result = new ArrayList<>();

        String sql = "SELECT game_id, shares, price_per_share, stock_name, stock_ticker, purchase_date, typ.type FROM trades AS tra " +
                "JOIN trade_type AS typ ON tra.type_id = typ.type_id " +
                "WHERE game_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId);

        while (rowSet.next()) {
            result.add(mapRowToTrade(rowSet));
        }

        return result;

    }

    @Override
    public void tradeStock(Trade trade) {
        //Insert Trade to DB

    }

    private TradeResponse mapRowToTrade(SqlRowSet rowSet) {
        TradeResponse result = new TradeResponse();
        result.setGameId(rowSet.getLong("game_id"));
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
        result.setTotalCost(rowSet.getBigDecimal("total_cost"));

        return result;
    }

}
