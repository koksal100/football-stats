package com.football.demo.repository;

import com.football.demo.entity.Appearance;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppearanceRepository extends JpaRepository<Appearance, String> {

    @Query("SELECT a.player, SUM(a.assists) as total_assists FROM Appearance a JOIN a.game g WHERE g.season = :year AND a.assists > 0 GROUP BY a.player ORDER BY total_assists DESC")
    List<Object[]> findTopAssistersBySeason(@Param("year") int year, Pageable pageable);

    @Query("SELECT a.player, SUM(a.redCards) as total_red_cards FROM Appearance a JOIN a.game g WHERE g.season = :year AND a.redCards > 0 GROUP BY a.player ORDER BY total_red_cards DESC")
    List<Object[]> findMostRedCardedPlayersBySeason(@Param("year") int year, Pageable pageable);

    @Query("SELECT a.player, SUM(a.goals) as total_goals FROM Appearance a JOIN a.game g WHERE g.season = :year AND a.playerClubId = :clubId AND a.goals > 0 GROUP BY a.player ORDER BY total_goals DESC")
    List<Object[]> findTopScorersByClubAndSeason(@Param("clubId") int clubId, @Param("year") int year, Pageable pageable);
}
