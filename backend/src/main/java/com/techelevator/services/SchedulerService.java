package com.techelevator.services;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class SchedulerService {

    @Scheduled(cron = "0 0 16 * * MON-FRI", zone = "US/Eastern")
//    @Scheduled(fixedDelay = 5000)
    public void tester() {
    }

    // Get all games ending on current date select gameid done
    // restTemplate call w/ Shyngis WebApiService to get closing prices
    // post sell trades for all users for all ending games at closing prices
    // Build TradeRequest with
    //      username (from database)
    //      gameId (from database)
    //      purchasePrice(from Share.getPrice() from webapiservice)
    //      String type = sell
    //

}
