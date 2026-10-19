package com.football.demo.util;

import com.football.demo.dto.ClubDto;
import com.football.demo.dto.CompetitionDto;
import com.football.demo.dto.GameDto;
import com.football.demo.dto.PlayerDto;
import com.football.demo.dto.TransferDto;
import com.football.demo.entity.Club;
import com.football.demo.entity.Competition;
import com.football.demo.entity.Game;
import com.football.demo.entity.Player;
import com.football.demo.entity.Transfer;

public class DtoConverter {

    public static PlayerDto convertPlayerToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setFirstName(player.getFirstName());
        playerDto.setLastName(player.getLastName());
        playerDto.setName(player.getName());
        playerDto.setLastSeason(player.getLastSeason());
        if (player.getCurrentClub() != null) {
            playerDto.setCurrentClubName(player.getCurrentClub().getName());
        }
        playerDto.setCountryOfBirth(player.getCountryOfBirth());
        playerDto.setCityOfBirth(player.getCityOfBirth());
        playerDto.setCountryOfCitizenship(player.getCountryOfCitizenship());
        playerDto.setDateOfBirth(player.getDateOfBirth());
        playerDto.setSubPosition(player.getSubPosition());
        playerDto.setPosition(player.getPosition());
        playerDto.setFoot(player.getFoot());
        playerDto.setHeightInCm(player.getHeightInCm());
        playerDto.setContractExpirationDate(player.getContractExpirationDate());
        playerDto.setAgentName(player.getAgentName());
        playerDto.setMarketValueInEur(player.getMarketValueInEur());
        playerDto.setHighestMarketValueInEur(player.getHighestMarketValueInEur());
        return playerDto;
    }

    public static ClubDto convertClubToDto(Club club) {
        ClubDto clubDto = new ClubDto();
        clubDto.setId(club.getId());
        clubDto.setClubCode(club.getClubCode());
        clubDto.setName(club.getName());
        clubDto.setCompetition(club.getCompetition());
        clubDto.setStadiumName(club.getStadiumName());
        clubDto.setCoachName(club.getCoachName());
        clubDto.setLastSeason(club.getLastSeason());
        return clubDto;
    }

    public static TransferDto convertTransferToDto(Transfer transfer) {
        TransferDto transferDto = new TransferDto();
        transferDto.setId(transfer.getId());
        transferDto.setPlayer(convertPlayerToDto(transfer.getPlayer()));
        transferDto.setTransferDate(transfer.getTransferDate());
        if (transfer.getFromClub() != null) {
            transferDto.setFromClub(convertClubToDto(transfer.getFromClub()));
        }
        if (transfer.getToClub() != null) {
            transferDto.setToClub(convertClubToDto(transfer.getToClub()));
        }
        transferDto.setTransferFee(transfer.getTransferFee());
        return transferDto;
    }

    public static CompetitionDto convertCompetitionToDto(Competition competition) {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setCompetitionId(competition.getCompetitionId());
        competitionDto.setCompetitionCode(competition.getCompetitionCode());
        competitionDto.setName(competition.getName());
        competitionDto.setCountryId(competition.getCountryId());
        competitionDto.setDomesticLeagueCode(competition.getDomesticLeagueCode());
        return competitionDto;
    }

    public static GameDto convertGameToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        if (game.getCompetition() != null) {
            gameDto.setCompetition(convertCompetitionToDto(game.getCompetition()));
        }
        gameDto.setSeason(game.getSeason());
        gameDto.setDate(game.getDate());
        if (game.getHomeClub() != null) {
            gameDto.setHomeClub(convertClubToDto(game.getHomeClub()));
        }
        if (game.getAwayClub() != null) {
            gameDto.setAwayClub(convertClubToDto(game.getAwayClub()));
        }
        gameDto.setHomeClubGoals(game.getHomeClubGoals());
        gameDto.setAwayClubGoals(game.getAwayClubGoals());
        gameDto.setHomeClubManagerName(game.getHomeClubManagerName());
        gameDto.setAwayClubManagerName(game.getAwayClubManagerName());
        gameDto.setStadium(game.getStadium());
        gameDto.setReferee(game.getReferee());
        gameDto.setHomeClubFormation(game.getHomeClubFormation());
        gameDto.setAwayClubFormation(game.getAwayClubFormation());
        gameDto.setAggregate(game.getAggregate());
        return gameDto;
    }
}
