package com.techelevator.model;

public class Game {

    private Long gameId;
    private String host;
    private String gameName;
    private boolean gameActive = true;
    private String startDate;
    private String endDate;

    public Game() {   }

    public Game(String gameName, String endDate) {
        this.gameName = gameName;
        this.endDate = endDate;
    }

    public Long getGameId() { return gameId; }

    public void setGameId(Long gameId) { this.gameId = gameId; }

    public String getHost() { return host; }

    public void setHost(String host) { this.host = host; }

    public String getGameName() { return gameName; }

    public void setGameName(String gameName) { this.gameName = gameName; }

    public boolean getGameActive() { return gameActive; }

    public void setGameActive(String gameStatus) { this.gameActive = gameActive; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }
}
