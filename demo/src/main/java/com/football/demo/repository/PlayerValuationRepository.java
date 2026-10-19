package com.football.demo.repository;

import com.football.demo.entity.PlayerValuation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerValuationRepository extends JpaRepository<PlayerValuation, Long> {

    @Query(value = "WITH MinMaxDates AS ( " +
            "    SELECT player_id, MIN(date) as min_date, MAX(date) as max_date " +
            "    FROM player_valuations " +
            "    WHERE SUBSTRING(date, 1, 4) = :year " +
            "    GROUP BY player_id " +
            "    HAVING COUNT(player_id) > 1 " +
            "), " +
            "StartEndValues AS ( " +
            "    SELECT " +
            "        mmd.player_id, " +
            "        sv.market_value_in_eur as start_value, " +
            "        ev.market_value_in_eur as end_value " +
            "    FROM MinMaxDates mmd " +
            "    JOIN player_valuations sv ON mmd.player_id = sv.player_id AND mmd.min_date = sv.date " +
            "    JOIN player_valuations ev ON mmd.player_id = ev.player_id AND mmd.max_date = ev.date " +
            ") " +
            "SELECT " +
            "    sev.player_id, " +
            "    (sev.end_value - sev.start_value) as increase " +
            "FROM StartEndValues sev " +
            "WHERE (sev.end_value - sev.start_value) > 0 " +
            "ORDER BY increase DESC", nativeQuery = true)
    List<Object[]> findMostRisingPlayersByYear(@Param("year") String year, Pageable pageable);

}
