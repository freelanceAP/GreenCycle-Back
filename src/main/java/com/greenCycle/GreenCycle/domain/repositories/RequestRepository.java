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

        // Query para traer el total de solicitudes cinco meses atras
        @Query(value = "SELECT MONTHNAME(r.date_time) AS month, COUNT(*) AS totalRequests " +
                        "FROM requests r " +
                        "WHERE r.date_time >= :startDate " +
                        "AND r.date_time <= :endDate " +
                        "GROUP BY MONTHNAME(r.date_time)", nativeQuery = true)

        List<Object[]> findRequestsByDateRange(
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

        // Query para traer el total de solicitudes por id
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

        // Query para traer el total por status
        // @Query(value = "SELECT " +
        //                 "COUNT(*) AS total_requests, " +
        //                 "SUM(CASE WHEN status = 'PENDING' THEN 1 ELSE 0 END) AS total_pending, " +
        //                 "SUM(CASE WHEN status = 'ACCEPTED' THEN 1 ELSE 0 END) AS total_accepted, " +
        //                 "SUM(CASE WHEN status = 'IN_COLLECTION' THEN 1 ELSE 0 END) AS total_in_collection, " +
        //                 "SUM(CASE WHEN status = 'IN_DISPOSITION' THEN 1 ELSE 0 END) AS total_in_disposition, " +
        //                 "SUM(CASE WHEN status = 'FINISHED' THEN 1 ELSE 0 END) AS total_finished " +
        //                 "FROM requests", nativeQuery = true)
        // StatusCountReq countRequestsByStatus();

}
