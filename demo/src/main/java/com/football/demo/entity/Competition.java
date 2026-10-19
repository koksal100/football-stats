package com.football.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "competitions")
public class Competition {

    @Id
    private String competitionId;
    @Column(name = "competition_code")
    private String competitionCode;
    private String name;
    @Column(name = "country_id")
    private int countryId;
    @Column(name = "domestic_league_code")
    private String domesticLeagueCode;
}