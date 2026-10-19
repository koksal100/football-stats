package com.football.demo.dto;

import lombok.Data;

@Data
public class AppearanceDto {
    private String id;
    private GameDto game;
    private PlayerDto player;
    private int playerCLubId;
    private String date;
    private String playerName;
    private CompetitionDto competition;
    private int yellowCards;
    private int redCards;
    private int goals;
    private int assists;
    private int minutesPlayed;
}
