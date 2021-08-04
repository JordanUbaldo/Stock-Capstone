package com.techelevator.model;

public class Game {

    private Long gameId;
    private Long host;
    private String gameName;
    private String gameStatus;
    private String startDate;
    private String endDate;

    public Game() {   }

    public Game(String gameName, String endDate) {
        this.gameName = gameName;
        this.endDate = endDate;
    }

    public Long getGameId() { return gameId; }

    public void setGameId(Long gameId) { this.gameId = gameId; }

    public Long getHost() { return host; }

    public void setHost(Long host) { this.host = host; }

    public String getGameName() { return gameName; }

    public void setGameName(String gameName) { this.gameName = gameName; }

    public String getGameStatus() { return gameStatus; }

    public void setGameStatus(String gameStatus) { this.gameStatus = gameStatus; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }
}
