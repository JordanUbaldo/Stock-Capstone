package com.techelevator.dao;

import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {
    int createGame(Game game);
    List<Game> viewGames(String username);
    boolean invitePlayers(String username, int gameId);
    boolean findGameByName(String gameName);
}
