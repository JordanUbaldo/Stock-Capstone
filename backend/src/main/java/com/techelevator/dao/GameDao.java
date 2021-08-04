package com.techelevator.dao;

import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {
    boolean createGame(Game game);
    List<Game> viewGames(String username);
    boolean invitePlayers(String username, Long gameId);
    boolean findGameByName(String gameName);
}
