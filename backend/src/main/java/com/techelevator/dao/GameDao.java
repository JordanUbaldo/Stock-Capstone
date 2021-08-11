package com.techelevator.dao;

import com.techelevator.model.Balance;
import com.techelevator.model.Game;
import com.techelevator.model.Player;
import com.techelevator.model.Trade;

import java.util.List;

public interface GameDao {
    int createGame(Game game);
    List<Game> viewGames(String username, String status);

    List<Integer> getAllActiveGameIds();

    List<Player> viewUsersInTheGame(int gameId);
    List<Balance> leaderboard(int gameId);
    boolean invitePlayers(String username, String status, int gameId);
    boolean findGameByName(String gameName);

    void addToPortfolioHistory(Balance portfolioSnapshot, int gameId);

    void changeGameStatusByGameId(int gameId);
    List<Balance> getBalancesByGameId(int gameId);
}
