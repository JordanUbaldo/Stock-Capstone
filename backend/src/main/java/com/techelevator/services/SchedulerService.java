package com.techelevator.services;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class SchedulerService {

    // Checks database every weekday at 4:00 pm US Eastern Standard Time, when the markets close.
    @Scheduled(cron = "0 0 16 * * MON-FRI", zone = "US/Eastern")
//    @Scheduled(fixedDelay = 5000)
    public void tester() {
        System.out.println("Hello");
        // Retrieve all games from the database ending on current date
        // Post sell trades for all users in each game, update balance.
    }

    // Get all games ending on current date
    // sell all stocks for all users

}
