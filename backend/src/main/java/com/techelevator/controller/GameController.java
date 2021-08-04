package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Game;
import com.techelevator.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@PreAuthorize("isAuthenticated()")
public class GameController {

    private GameDao gameDao;
    private UserDao userDao;

    public GameController(GameDao gameDao, UserDao userDao) {
        this.gameDao = gameDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/games", method = RequestMethod.GET)
    public List<Game> viewGames(Principal principal) {
        return gameDao.viewGames(principal.getName());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public boolean createGame(@Valid @RequestBody Game game) {
        return gameDao.createGame(game);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/games/{gameId}/invite", method = RequestMethod.POST)
    public boolean invitePlayers(@Valid @RequestBody Player player) {
        return gameDao.invitePlayers(player.getUsername(), player.getGameId());
    }

}
