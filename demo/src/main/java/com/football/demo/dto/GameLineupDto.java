package com.football.demo.dto;

import lombok.Data;

@Data
public class GameLineupDto {
    private String id;
    private String date;
    private GameDto game;
    private PlayerDto player;
    private ClubDto club;
    private String type;
    private String position;
    private String number;
    private int teamCaptain;
}
