package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.Player;

import java.util.List;

public interface GameDao {
    int createGame(Game game);
    List<Game> viewGames(String username, String status);
    List<Player> viewUsersInTheGame(int gameId);
    boolean invitePlayers(String username, String status, int gameId);
    boolean findGameByName(String gameName);
}
