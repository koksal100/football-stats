package com.football.demo.repository;

import com.football.demo.entity.GameEvent;
import com.football.demo.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, String> {

    @Query("SELECT ge.player, COUNT(ge.player) as goals FROM GameEvent ge JOIN ge.game g WHERE g.season = :year AND ge.type = 'Goals' GROUP BY ge.player ORDER BY goals DESC")
    List<Object[]> findTopScorersBySeason(@Param("year") int year, Pageable pageable);
}
