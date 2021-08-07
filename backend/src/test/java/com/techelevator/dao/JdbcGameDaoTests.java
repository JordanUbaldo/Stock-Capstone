package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcGameDaoTests extends FinalCapstoneDaoTests{

    private JdbcGameDao sut;
    private static final Game GAME_1 = new Game("testGame", "2021-10-25");
    private static final Game GAME_2 = new Game("testGame2", "2021-10-25");
    private static Game gameTest = new Game("test", "2021-10-25");
    private static final User USER_1 = new User(1L, "test1", "test1", "authorities1");

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcGameDao(jdbcTemplate);
    }

//    @Test
//    public void find_all_games_by_username() {
//        List<Game> expected = new ArrayList<>();
//        expected.add(GAME_1);
//        expected.add(GAME_2);
//        Assert.assertEquals(expected.size(), sut.viewGames("final_capstone_appuser").size());
//    }

    @Test
    public void find_all_users_by_gameId() {
        List<User> expected = new ArrayList<>();
        expected.add(USER_1);
        Assert.assertEquals(expected.size(), sut.viewUsersInTheGame(1).size());
    }

    @Test
    public void create_game() {
        gameTest.setHost("final_capstone_appuser");
        Assert.assertEquals(3, sut.createGame(gameTest));
    }
}
