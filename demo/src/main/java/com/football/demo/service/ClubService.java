package com.football.demo.service;


import com.football.demo.dto.ClubDto;
import com.football.demo.dto.PlayerDto;
import com.football.demo.dto.TransferDto;
import com.football.demo.entity.Club;
import com.football.demo.entity.Player;
import com.football.demo.entity.Transfer;
import com.football.demo.repository.AppearanceRepository;
import com.football.demo.repository.ClubRepository;
import com.football.demo.repository.GameRepository;
import com.football.demo.repository.TransferRepository;
import com.football.demo.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClubService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Autowired
    private TransferRepository transferRepository;


    public List<ClubDto> getMostScorerTeamsByYear(int year, int size){
        Pageable pageable = PageRequest.of(0, size);
        List<Object[]> topScorerTeamsData = gameRepository.findMostScorerTeamsBySeason(year, pageable);

        List<Integer> clubIds = topScorerTeamsData.stream()
                .map(record -> ((Number) record[0]).intValue())
                .collect(Collectors.toList());

        Map<Integer, Club> clubsMap = clubRepository.findAllById(clubIds).stream()
                .collect(Collectors.toMap(Club::getId, Function.identity()));

        return topScorerTeamsData.stream()
                .map(record -> {
                    Integer clubId = ((Number) record[0]).intValue();
                    BigDecimal totalGoals = new BigDecimal(((Number) record[1]).toString());

                    Club club = clubsMap.get(clubId);
                    ClubDto clubDto = DtoConverter.convertClubToDto(club);

                    HashMap<String, String> requestedMap = new HashMap<>();
                    requestedMap.put("total_goals", totalGoals.toPlainString());
                    requestedMap.put("year", String.valueOf(year));

                    clubDto.setRequestedInformations(requestedMap);

                    return clubDto;
                })
                .collect(Collectors.toList());
    }

    public List<PlayerDto> getMostScorerPlayers(int clubId, int year, int size) {
        Pageable pageable = PageRequest.of(0, size);
        List<Object[]> topScorersData = appearanceRepository.findTopScorersByClubAndSeason(clubId, year, pageable);

        return topScorersData.stream()
                .map(record -> {
                    Player player = (Player) record[0];
                    BigDecimal goals = new BigDecimal(((Number) record[1]).toString());

                    PlayerDto playerDto = DtoConverter.convertPlayerToDto(player);

                    HashMap<String, String> requestedMap = new HashMap<>();
                    requestedMap.put("goals", goals.toPlainString());
                    requestedMap.put("year", String.valueOf(year));

                    playerDto.setRequestedInformations(requestedMap);
                    return playerDto;
                })
                .collect(Collectors.toList());
    }

    public List<TransferDto> getTransfersByYear(Integer clubId, int year) {
        List<Transfer> transfers = transferRepository.findByClubIdAndYear(clubId, String.valueOf(year));
        return transfers.stream()
                .map(DtoConverter::convertTransferToDto)
                .collect(Collectors.toList());
    }
}
