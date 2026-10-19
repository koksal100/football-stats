package com.football.demo.controller;


import com.football.demo.dto.ClubDto;
import com.football.demo.dto.PlayerDto;
import com.football.demo.dto.TransferDto;
import com.football.demo.service.ClubService;
import com.football.demo.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private TransferService transferService;
    @GetMapping("/most-scorer-clubs-by-year")
    public List<ClubDto> getMostScorerTeamsByYear(@RequestParam int year, @RequestParam int size){
        return clubService.getMostScorerTeamsByYear(year,size);
    }

    @GetMapping("/most-scorer-players/{clubId}")
    public List<PlayerDto> getMostScorerPlayers(@PathVariable int clubId, @RequestParam int year, @RequestParam int size){
        return clubService.getMostScorerPlayers(clubId, year, size);
    }

    @GetMapping("/transfers/{clubId}")
    public List<TransferDto> getTransfersByYear(@PathVariable int clubId, @RequestParam int year) {
        return clubService.getTransfersByYear(clubId, year);
    }



}
