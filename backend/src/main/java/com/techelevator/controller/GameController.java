package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class GameController {

    private GameDao gameDao;
    private UserDao userDao;

    public GameController(GameDao gameDao, UserDao userDao) {
        this.gameDao = gameDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/games/{status}", method = RequestMethod.GET)
    public List<Game> viewGames( @Valid @PathVariable String status, Principal principal) {
//        if (status == "accepted") {
//            status = "Accepted";
//        } else if (status == "pending") {
//            status = "Pending";
//        } else {
//            status = "Declined";
//        }
// Random comment to test git.
        return gameDao.viewGames(principal.getName(), status);
    }

    @RequestMapping(path = "/games/{gameId}/users", method = RequestMethod.GET)
    public List<Player> viewPlayers(@Valid @PathVariable int gameId, Principal principal) {
        return gameDao.viewUsersInTheGame(gameId, principal.getName());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public int createGame(@Valid @RequestBody Game game, Principal principal) {
        game.setHost(principal.getName());
        return gameDao.createGame(game);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/games/{gameId}/invite", method = RequestMethod.POST)
    public boolean invitePlayers(@Valid @PathVariable int gameId, @RequestBody Player player, Principal principal) {
        return gameDao.invitePlayers(player.getUsername(), player.getStatus(), gameId, principal.getName());
    }

    @RequestMapping(path = "/games/{gameId}/balance", method = RequestMethod.GET)
    public List<Balance> getBalancesByGameId(@Valid @PathVariable int gameId, Principal principal) {
        return gameDao.getBalancesByGameId(gameId, principal.getName());
    }

    @RequestMapping(path = "/games/{gameId}/leaderboard", method = RequestMethod.GET)
    public List<Balance> leaderboard(@Valid @PathVariable int gameId, Principal principal) {

        return gameDao.leaderboard(gameId, principal.getName());
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/games/high-scores", method = RequestMethod.GET)
    public List<Balance> highScores() {
        return gameDao.getHighScores();
    }

//    @RequestMapping(path = "/games/{ticker}/price", method = RequestMethod.GET)
//    public Share getPrice(@Valid @PathVariable String ticker) {
//        WebApiService price = new WebApiService();
//        return price.getPrice(ticker);
//    }

}
