package com.football.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.football.demo.entity.Club;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDto extends AbstractDto{
    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private int lastSeason;
    private String currentClubName;
    private String countryOfBirth;
    private String cityOfBirth;
    private String countryOfCitizenship;
    private String dateOfBirth;
    private String subPosition;
    private String position;
    private String foot;
    private float heightInCm;
    private String contractExpirationDate;
    private String agentName;
    private float marketValueInEur;
    private float highestMarketValueInEur;
}
