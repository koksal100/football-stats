package com.football.demo.controller;


import com.football.demo.dto.PlayerDto;
import com.football.demo.entity.Player;
import com.football.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;


    @RequestMapping("/scorers")
    public List<PlayerDto> getMostScorerPlayersByYear(@RequestParam int year, @RequestParam int size){
        return playerService.getMostScorerPlayersByYear(year,size);
    }

    @RequestMapping("/rising")
    public List<PlayerDto> getMostRisingPlayersByYear(@RequestParam int year, @RequestParam int size){
        return playerService.getMostRisingPlayersByYear(year,size);
    }

    @RequestMapping("/reds")
    public List<PlayerDto> getMostRedCardedPlayersByYear(@RequestParam int year, @RequestParam int size){
        return playerService.getMostRedCardedPlayersByYear(year,size);
    }

    @RequestMapping("/assisters")
    public List<PlayerDto> getTopAssistersByYear(@RequestParam int year, @RequestParam int size){
        return playerService.getTopAssistersByYear(year,size);
    }








}
