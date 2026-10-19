package com.football.demo.service;

import com.football.demo.dto.GameDto;
import com.football.demo.entity.Game;
import com.football.demo.repository.GameRepository;
import com.football.demo.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService {

    @Autowired
    private GameRepository gameRepository;

    public Page<GameDto> getGamesByCompetitionAndSeason(String competitionId, int season, Pageable pageable) {
        List<Game> games = gameRepository.findByCompetitionCompetitionIdAndSeason(competitionId, season, pageable);
        List<GameDto> gameDtos = games.stream()
                .map(DtoConverter::convertGameToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(gameDtos, pageable, gameDtos.size());
    }
}
