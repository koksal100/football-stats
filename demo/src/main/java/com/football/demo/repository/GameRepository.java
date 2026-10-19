package com.football.demo.repository;

import com.football.demo.entity.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(value = "SELECT club_id, SUM(goals) as total_goals FROM ( " +
            "    SELECT home_club_id as club_id, home_club_goals as goals FROM games WHERE season = :year " +
            "    UNION ALL " +
            "    SELECT away_club_id as club_id, away_club_goals as goals FROM games WHERE season = :year " +
            ") as all_goals " +
            "GROUP BY club_id " +
            "ORDER BY total_goals DESC", nativeQuery = true)
    List<Object[]> findMostScorerTeamsBySeason(@Param("year") int year, Pageable pageable);

    List<Game> findByCompetitionCompetitionIdAndSeason(String competitionId, int season, Pageable pageable);
}
