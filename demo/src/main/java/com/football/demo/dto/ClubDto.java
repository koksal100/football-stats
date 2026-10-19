package com.football.demo.dto;

import com.football.demo.entity.Competition;
import lombok.Data;

@Data
public class ClubDto extends AbstractDto {
    private int id;
    private String clubCode;
    private String name;
    private Competition competition;
    private String stadiumName;
    private String coachName;
    private String lastSeason;
}
