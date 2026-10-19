package com.football.demo.repository;

import com.football.demo.entity.GameLineup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameLineupRepository extends JpaRepository<GameLineup, String> {
}
