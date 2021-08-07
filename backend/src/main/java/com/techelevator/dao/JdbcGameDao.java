package com.techelevator.dao;

import com.techelevator.model.Balance;
import com.techelevator.model.Game;
import com.techelevator.model.Player;
import com.techelevator.model.Trade;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao{

    // objects to access database
    private JdbcTemplate jdbcTemplate;


    public JdbcGameDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createGame(Game game) {
        int newGameId = 0;

        if(this.findGameByName(game.getGameName())) {
            String games = "INSERT INTO games (game_name, host, end_date) " +
                    "VALUES (?, ?, ?) RETURNING game_id;";
            String userStatus = "INSERT INTO user_status (game_id, username, user_status) " +
                    "VALUES (? ,?, ?);";
            String balances = "INSERT INTO balances (game_id, username) VALUES (?, ?);";
            String status = "Accepted";
            try {
                newGameId = jdbcTemplate.queryForObject(games, Integer.class, game.getGameName(), game.getHost(), LocalDate.parse(game.getEndDate()));
                jdbcTemplate.update(userStatus, newGameId, game.getHost(), status);
                jdbcTemplate.update(balances, newGameId, game.getHost());
            } catch (NullPointerException j) {
                System.out.println("Null data caught! " + j.getMessage());
            } catch (DataAccessException e) {
                System.out.println("Error accessing data " + e.getMessage());
            }
        }
        return newGameId;
    }

    @Override
    public List<Game> viewGames(String username, String status) {
        // viewGames returns with a list of games
        List<Game> games = new ArrayList<>();
        String sql = "SELECT g.game_id, g.game_name, g.game_active, g.host, g.start_date, g.end_date " +
                "FROM games g " +
                "JOIN user_status s ON g.game_id = s.game_id " +
                "JOIN users u ON s.username = u.username " +
                // we show only games user have joined
                "WHERE u.username = ? AND s.user_status = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, status);
            while (results.next()) {
                // helper method converts database response into Game object
                Game game = mapRowToGame(results);
                // adding each game object to the list
                games.add(game);
            }
        } catch (NullPointerException e) {
            System.out.println("Error occurred! " + e.getMessage());
        }
        return games;
    }

    @Override
    public List<Player> viewUsersInTheGame(int gameId) {
        List<Player> users = new ArrayList<>();
        String sql = "SELECT game_id, username, user_status FROM user_status WHERE game_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
            while (results.next()) {
                Player player = mapRowToPlayer(results);
                users.add(player);
            }
        } catch (NullPointerException e) {
            System.out.println("Error occurred! " + e.getMessage());
        }
        return users;
    }

    @Override
    public boolean invitePlayers(String username, String status, int gameId) {
        // returning false if method fails
        boolean result = false;
        String sql;
        // default status for invited players - "Pending"
        if (status.equals("Pending")) {
            sql = "INSERT INTO user_status (game_id, username, user_status) VALUES (?, ?, ?);";
            try {
                jdbcTemplate.update(sql, gameId, username, status);
                // if successful - turning result boolean to true
                result = true;
            } catch (DataAccessException e) {
                System.out.println("Error accessing data " + e.getMessage());
            }
        } else if (status.equals("Accepted")) {
            sql = "UPDATE user_status SET user_status = 'Accepted' WHERE username = ? AND game_id = ?;";
            try {
                jdbcTemplate.update(sql, username, gameId);
                // if successful - turning result boolean to true
                result = true;
            } catch (DataAccessException e) {
                System.out.println("Error accessing data " + e.getMessage());
            }
        } else {
            sql = "UPDATE user_status SET user_status = 'Declined' WHERE username = ? AND game_id = ?;";
            try {
                jdbcTemplate.update(sql, username, gameId);
                // if successful - turning result boolean to true
                result = true;
            } catch (DataAccessException e) {
                System.out.println("Error accessing data " + e.getMessage());
            }
        }
        return result;
    }


    @Override
    // this method created to help createGame method identify if game name available
    public boolean findGameByName(String gameName) {
        // default value true confirming that name available
        boolean result = true;
        String sql = "SELECT game_name FROM games WHERE game_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameName);
            // checking if our query has returned with anything
            if (results.next()) {
                // if there is a game with that name - turning our boolean to false meaning this name is not available
                result = false;
            }
        } catch (NullPointerException e) {
            System.out.println("Error occurred! " + e.getMessage());
        }
        return result;
    }
    @Override
    public List<Balance> getBalancesByGameId(int gameId) {
        List<Balance> balances = new ArrayList<>();
        String sql = "SELECT balance_id, game_id, username, amount FROM balances WHERE game_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
            while(results.next()) {
                Balance balance = mapRowToBalance(results);
                balances.add(balance);
            }
        } catch (NullPointerException e) {
            System.out.println("Error occurred! " + e.getMessage());
        } catch (DataAccessException j) {
            System.out.println("Data Access error! " + j.getMessage());
        }
        return balances;
    }

    @Override
    public List<Trade> leaderboard(int gameId) {
        List<Trade> leaders = new ArrayList<>();

        String sql = "SELECT DISTINCT s.username, s.game_id, s.stock_ticker, s.stock_name, s.shares, b.amount " +
                "FROM stocks s " +
                "JOIN balances b ON s.game_id = b.game_id AND s.username = b.username " +
                "WHERE s.game_id = ? " +
                "ORDER BY amount DESC " +
                "LIMIT 10;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
        while(results.next()) {
            // helper method converts database response into Game object
            Trade trade = mapRowToLeaders(results);
            // adding each game object to the list
            leaders.add(trade);
        }

        return leaders;
    }

    @Override
    public void changeGameStatusByGameId(int gameId) {
        String sql = "UPDATE games SET game_active = false WHERE game_id = ?;";
        try {
            jdbcTemplate.update(sql, gameId);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    //helper method to create Player object from database response
    private Player mapRowToPlayer (SqlRowSet p) {
        Player player = new Player();
        player.setUsername(p.getString("username"));
        player.setGameId(p.getInt("game_id"));
        player.setStatus(p.getString("user_status"));
        return player;
    }

    // helper method to create Game object from database response
    private Game mapRowToGame(SqlRowSet g) {
        Game game = new Game();
        game.setGameId(g.getInt("game_id"));
        game.setGameName(g.getString("game_name"));
        game.setGameActive(g.getBoolean("game_active"));
        game.setHost(g.getString("host"));
        game.setStartDate(g.getString("start_date"));
        game.setEndDate(g.getString("end_date"));
        return game;
    }

    private Trade mapRowToLeaders (SqlRowSet l) {
        Trade trade = new Trade();
        //s.username, s.game_id, s.stock_ticker, s.stock_name, s.shares, b.amount
        trade.setUserId(l.getString("username"));
        trade.setGameId(l.getInt("game_id"));
        trade.setStockTicker(l.getString("stock_ticker"));
        trade.setSharesNumber(l.getInt("shares"));
        trade.setAmount(l.getBigDecimal("amount"));
        return trade;
    }

    private Balance mapRowToBalance(SqlRowSet b) {
        Balance balance = new Balance();
        balance.setBalanceId(b.getInt("balance_id"));
        balance.setGameId(b.getInt("game_id"));
        balance.setUsername(b.getString("username"));
        balance.setAmount(b.getBigDecimal("amount"));
        return balance;
    }


}
