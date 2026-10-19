package com.football.demo.controller;

import com.football.demo.dto.GameDto;
import com.football.demo.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/{competitionId}/games")
    public Page<GameDto> getGamesByCompetitionAndSeason(@PathVariable String competitionId, @RequestParam int season, Pageable pageable) {
        return competitionService.getGamesByCompetitionAndSeason(competitionId, season, pageable);
    }
}