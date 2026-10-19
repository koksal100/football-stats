package com.football.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferDto extends AbstractDto {
    private Long id;
    private PlayerDto player;
    private String transferDate;
    private ClubDto fromClub;
    private ClubDto toClub;
    private Float transferFee;
}
