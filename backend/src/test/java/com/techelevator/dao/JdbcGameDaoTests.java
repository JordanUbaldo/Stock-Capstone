package com.techelevator.dao;

import com.techelevator.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcGameDaoTests extends FinalCapstoneDaoTests{

    private JdbcGameDao sut;
    private GameDao gameDao;
    private static final Game GAME_1 = new Game("testGame", "2021-10-25");
    private static final Game GAME_2 = new Game("testGame2", "2021-10-25");

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcGameDao(dataSource, gameDao);
    }

    @Test
    public void find_all_games_by_username() {

    }

}
