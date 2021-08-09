package com.techelevator.dao;

import com.techelevator.model.Balance;
import com.techelevator.model.Game;
import com.techelevator.model.Player;
import com.techelevator.model.Trade;

import java.util.List;

public interface GameDao {
    int createGame(Game game);
    List<Game> viewGames(String username, String status);
    List<Player> viewUsersInTheGame(int gameId, String username);
    List<Balance> leaderboard(int gameId, String username);
    boolean invitePlayers(String username, String status, int gameId, String currentUser);
    boolean findGameByName(String gameName);
    Game findGameByGameId(int gameId);
    void changeGameStatusByGameId(int gameId);
    List<Balance> getBalancesByGameId(int gameId, String username);
}
