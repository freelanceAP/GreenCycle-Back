package com.greenCycle.GreenCycle.domain.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenCycle.GreenCycle.domain.entities.RequestEntity;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {
    @Query(value = "SELECT MONTHNAME(r.dateTime) AS month, COUNT(*) AS totalRequests" +
            "FROM requests r " +
            "WHERE r.dateTime >= :startDate " +
            "AND r.dateTime <= :endDate " +
            "GROUP BY MONTHNAME(r.dateTime)", nativeQuery = true)

    List<Object[]> findRequestsByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
