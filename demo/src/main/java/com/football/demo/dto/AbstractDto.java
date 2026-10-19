package com.football.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data
public abstract class AbstractDto {
    @JsonProperty("Requested Informations")
    Map<String,String> requestedInformations=null;
}
