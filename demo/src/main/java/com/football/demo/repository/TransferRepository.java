package com.football.demo.repository;

import com.football.demo.entity.Transfer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query("SELECT t FROM Transfer t WHERE SUBSTRING(t.transferDate, 1, 4) = :year AND t.transferFee IS NOT NULL ORDER BY t.transferFee DESC")
    List<Transfer> findMostValuableTransfersByYear(@Param("year") String year, Pageable pageable);

    @Query("SELECT t FROM Transfer t WHERE (t.fromClub.id = :clubId OR t.toClub.id = :clubId) AND SUBSTRING(t.transferDate, 1, 4) = :year")
    List<Transfer> findByClubIdAndYear(@Param("clubId") Integer clubId, @Param("year") String year);


}
