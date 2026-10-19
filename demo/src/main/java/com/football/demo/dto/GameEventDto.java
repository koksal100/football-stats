package com.football.demo.dto;

import lombok.Data;

@Data
public class GameEventDto {
    private String id;
    private String date;
    private GameDto game;
    private int minute;
    private String type;
    private ClubDto club;
    private PlayerDto player;
    private String description;
    private float playerInId;
    private float playerAssistId;
}
