package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.Player;
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
        // findGameByName returns with boolean - true if the name is available; false if name already exists
        if(this.findGameByName(game.getGameName())) {
            String games = "INSERT INTO games (game_name, host, end_date) " +
                    "VALUES (?, ?, ?) RETURNING game_id;";
            String userStatus = "INSERT INTO user_status (game_id, username, user_status) " +
                    "VALUES (? ,?, ?);";
            String balances = "INSERT INTO balances (game_id, username) VALUES (?, ?);";
            String status = "Accepted";
            try {
                newGameId = jdbcTemplate.queryForObject(games, Integer.class, game.getGameName(), game.getHost(), LocalDate.parse(game.getEndDate()));
                jdbcTemplate.queryForRowSet(userStatus, newGameId, game.getHost(), status);
                jdbcTemplate.queryForRowSet(balances, newGameId, game.getHost());
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
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, status);
        while(results.next()) {
            // helper method converts database response into Game object
            Game game = mapRowToGame(results);
            // adding each game object to the list
            games.add(game);
        }
        return games;
    }

    @Override
    public List<Player> viewUsersInTheGame(int gameId) {
        List<Player> users = new ArrayList<>();
        String sql = "SELECT game_id, username, user_status FROM user_status WHERE game_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
        while(results.next()) {
            Player player = mapRowToPlayer(results);
            users.add(player);
        }
        return users;
    }

    @Override
    public boolean invitePlayers(String username, int gameId) {
        // returning false if method fails
        boolean result = false;
        // default status for invited players - "Pending"
        String status = "Pending";
        String sql = "INSERT INTO user-status (game_id, username, user_status) VALUES (?, ?, ?);";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId, username, status);
            // if successful - turning result boolean to true
            result = true;
        } catch (DataAccessException e) {
            System.out.println("Error accessing data " + e.getMessage());
        }
        return result;
    }


    @Override
    // this method created to help createGame method identify if game name available
    public boolean findGameByName(String gameName) {
        // default value true confirming that name available
        boolean result = true;
        String sql = "SELECT game_name FROM games WHERE game_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameName);
        // checking if our query has returned with anything
        if(results.next()) {
            // if there is a game with that name - turning our boolean to false meaning this name is not available
            result = false;
        }
        return result;
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


}
