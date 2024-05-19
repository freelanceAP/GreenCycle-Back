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
        @Query(value = "SELECT MONTHNAME(r.date_time) AS month, COUNT(*) AS totalRequests " + // Añadido espacio después
                                                                                              // de totalRequests
                        "FROM requests r " +
                        "WHERE r.date_time >= :startDate " +
                        "AND r.date_time <= :endDate " +
                        "GROUP BY MONTHNAME(r.date_time)", nativeQuery = true)

        List<Object[]> findRequestsByDateRange(
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

        @Query(value = "SELECT MONTHNAME(r.date_time) AS month, COUNT(*) AS totalRequests " +
                        "FROM requests r " +
                        "WHERE r.date_time >= :startDate " +
                        "AND r.date_time <= :endDate " +
                        "AND r.user_id = :id " +
                        "GROUP BY MONTHNAME(r.date_time)", nativeQuery = true)
        List<Object[]> findRequestsByDateRangeById(
                        @Param("id") Long id,
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);
}
