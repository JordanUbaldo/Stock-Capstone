package com.techelevator.dao;

import com.techelevator.model.Game;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao{

    // objects to access database
    private JdbcTemplate jdbcTemplate;
    private GameDao gameDao;


    public JdbcGameDao (DataSource dataSource, GameDao gameDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.gameDao = gameDao;
    }

    @Override
    public boolean createGame(Game game) {
        // boolean to confirm we created a game
        boolean result = false;
        // findGameByName returns with boolean - true if the name is available; false if name already exists
        if(gameDao.findGameByName(game.getGameName())) {
            String games = "INSERT INTO games (game_name, game_status, host, start_date, end_date) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING game_id;";
            String userStatus = "INSERT INTO user_status (game_id, username, user_status) " +
                    "VALUES (? ,?, ?);";
            String balances = "INSERT INTO balances (game_id, username) VALUES (?, ?);";
            String status = "Accepted";
            Long newGameId;
            try {
                newGameId = jdbcTemplate.queryForObject(games, Long.class, game.getGameName(), game.getGameStatus(), game.getHost(), game.getStartDate(), game.getEndDate());
                jdbcTemplate.queryForRowSet(userStatus, game.getGameId(), game.getHost(), status);
                jdbcTemplate.queryForRowSet(balances, game.getGameId(), game.getHost());
                // if all processes successful we switch result boolean to true
                result = true;
            } catch(DataAccessException e) {
                System.out.println("Error accessing data " + e.getMessage());
            }
        }
        return result;
    }
    @Override
    public List<Game> viewGames(String username) {
        // viewGames returns with a list of games
        List<Game> games = new ArrayList<>();
        String sql = "SELECT game_name, end_date " +
                "FROM games g " +
                "JOIN user_status s ON g.game_id = s.game_id " +
                "JOIN users u ON s.username = u.username " +
                // we show only games user have joined
                "WHERE u.username = ? && p.user_status = 'Accepted';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while(results.next()) {
            // helper method converts database response into Game object
            Game game = mapRowToGame(results);
            // adding each game object to the list
            games.add(game);
        }
        return games;
    }

    @Override
    public boolean invitePlayers(String username, Long gameId) {
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

    // helper method to create Game object from database response
    private Game mapRowToGame(SqlRowSet g) {
        Game game = new Game();
        game.setGameName(g.getString("game_name"));
        game.setEndDate(g.getString("end_date"));
        return game;
    }


}