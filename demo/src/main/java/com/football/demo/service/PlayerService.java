package com.football.demo.service;

import com.football.demo.dto.PlayerDto;
import com.football.demo.entity.Player;
import com.football.demo.repository.AppearanceRepository;
import com.football.demo.repository.GameEventRepository;
import com.football.demo.repository.PlayerRepository;
import com.football.demo.repository.PlayerValuationRepository;
import com.football.demo.util.DtoConverter;
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
public class PlayerService {

    private final GameEventRepository gameEventRepository;
    private final PlayerRepository playerRepository;
    private final PlayerValuationRepository playerValuationRepository;
    private final AppearanceRepository appearanceRepository;

    public PlayerService(GameEventRepository gameEventRepository, PlayerRepository playerRepository, PlayerValuationRepository playerValuationRepository, AppearanceRepository appearanceRepository) {
        this.gameEventRepository = gameEventRepository;
        this.playerRepository = playerRepository;
        this.playerValuationRepository = playerValuationRepository;
        this.appearanceRepository = appearanceRepository;
    }

    public List<PlayerDto> getMostScorerPlayersByYear(int year, int size) {
        Pageable pageable = PageRequest.of(0, size);
        List<Object[]> topScorersData = gameEventRepository.findTopScorersBySeason(year, pageable);

        return topScorersData.stream()
                .map(record -> {
                    Player player = (Player) record[0];
                    Long goals = (Long) record[1];
                    HashMap<String, String> requestedMap = new HashMap<>();
                    requestedMap.put("goals", goals.toString());
                    requestedMap.put("year", Integer.toString(year));
                    PlayerDto playerDto = DtoConverter.convertPlayerToDto(player);
                    playerDto.setRequestedInformations(requestedMap);

                    return playerDto;
                })
                .collect(Collectors.toList());
    }

    public List<PlayerDto> getMostRisingPlayersByYear(int year, int size) {
        Pageable pageable = PageRequest.of(0, size);
        List<Object[]> risingPlayersData = playerValuationRepository.findMostRisingPlayersByYear(String.valueOf(year), pageable);

        List<Long> playerIds = risingPlayersData.stream()
                .map(record -> ((Number) record[0]).longValue())
                .collect(Collectors.toList());

        Map<Long, Player> playersMap = playerRepository.findAllById(playerIds).stream()
                .collect(Collectors.toMap(Player::getId, Function.identity()));

        return risingPlayersData.stream()
                .map(record -> {
                    Long playerId = ((Number) record[0]).longValue();
                    BigDecimal increase = new BigDecimal(((Number) record[1]).toString());

                    Player player = playersMap.get(playerId);
                    PlayerDto playerDto = DtoConverter.convertPlayerToDto(player);

                    HashMap<String, String> requestedMap = new HashMap<>();
                    requestedMap.put("value_increase_in_eur", increase.toPlainString());
                    requestedMap.put("year", String.valueOf(year));

                    playerDto.setRequestedInformations(requestedMap);

                    return playerDto;
                })
                .collect(Collectors.toList());
    }

    public List<PlayerDto> getTopAssistersByYear(int year, int size) {
        Pageable pageable = PageRequest.of(0, size);
        List<Object[]> topAssistersData = appearanceRepository.findTopAssistersBySeason(year, pageable);
        return processStatList(topAssistersData, "assists", year);
    }

    public List<PlayerDto> getMostRedCardedPlayersByYear(int year, int size) {
        Pageable pageable = PageRequest.of(0, size);
        List<Object[]> mostRedCardedData = appearanceRepository.findMostRedCardedPlayersBySeason(year, pageable);
        return processStatList(mostRedCardedData, "red_cards", year);
    }

    private List<PlayerDto> processStatList(List<Object[]> data, String statName, int year) {
        return data.stream()
                .map(record -> {
                    Player player = (Player) record[0];
                    BigDecimal count = new BigDecimal(((Number) record[1]).toString());

                    PlayerDto playerDto = DtoConverter.convertPlayerToDto(player);

                    HashMap<String, String> requestedMap = new HashMap<>();
                    requestedMap.put(statName, count.toPlainString());
                    requestedMap.put("year", String.valueOf(year));

                    playerDto.setRequestedInformations(requestedMap);
                    return playerDto;
                })
                .collect(Collectors.toList());
    }
}
