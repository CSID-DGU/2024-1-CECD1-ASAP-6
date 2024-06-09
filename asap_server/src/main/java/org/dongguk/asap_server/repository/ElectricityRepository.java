package org.dongguk.asap_server.repository;

import org.dongguk.asap_server.domain.Electricity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ElectricityRepository extends JpaRepository<Electricity, Long> {
    @Query("SELECT e.at, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.section = :section " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY e.at " +
            "ORDER BY e.at DESC")
    List<Object[]> findAverageElectricityUsageBySectionAndDate(
            @Param("section") String section,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as weekStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.section = :section " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY WEEK(e.at, 1) " +
            "ORDER BY weekStart")
    List<Object[]> findWeeklyAverageElectricityUsageBySection(
            @Param("section") String section,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as monthStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.section = :section " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY YEAR(e.at), MONTH(e.at) " +
            "ORDER BY monthStart")
    List<Object[]> findMonthlyAverageElectricityUsageBySection(
            @Param("section") String section,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
