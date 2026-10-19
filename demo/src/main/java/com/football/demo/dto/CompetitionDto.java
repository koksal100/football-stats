package com.football.demo.dto;

import lombok.Data;

@Data
public class CompetitionDto {
    private String competitionId;
    private String competitionCode;
    private String name;
    private int countryId;
    private String domesticLeagueCode;
}
