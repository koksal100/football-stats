package com.football.demo.dto;

import lombok.Data;

@Data
public class PlayerValuationDto {
    private Long id;
    private PlayerDto player;
    private String date;
    private int marketValueInEur;
}
