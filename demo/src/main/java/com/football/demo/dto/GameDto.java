package com.football.demo.dto;

import lombok.Data;

@Data
public class GameDto {
    private int id;
    private CompetitionDto competition;
    private int season;
    private String date;
    private ClubDto homeClub;
    private ClubDto awayClub;
    private float homeClubGoals;
    private float awayClubGoals;
    private String homeClubManagerName;
    private String awayClubManagerName;
    private String stadium;
    private String referee;
    private String homeClubFormation;
    private String awayClubFormation;
    private String aggregate;
}
