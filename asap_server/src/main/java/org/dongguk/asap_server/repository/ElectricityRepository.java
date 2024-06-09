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

    // 구 평균 전력 사용량 검색
    @Query("SELECT DATE(e.at), AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.section = :section " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(e.at) " +
            "ORDER BY DATE(e.at) DESC")
    List<Object[]> findAverageElectricityUsageBySectionAndDate(
            @Param("section") String section,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as weekStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.section = :section " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY WEEK(e.at) " +
            "ORDER BY weekStart DESC")
    List<Object[]> findWeeklyAverageElectricityUsageBySection(
            @Param("section") String section,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as monthStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.section = :section " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY YEAR(e.at), MONTH(e.at) " +
            "ORDER BY monthStart DESC")
    List<Object[]> findMonthlyAverageElectricityUsageBySection(
            @Param("section") String section,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
    ////////////////////

    // 가구별 평균 전력 사용량
    @Query("SELECT DATE(e.at), AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.id = :id " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(e.at) " +
            "ORDER BY DATE(e.at) DESC")
    List<Object[]> findAverageElectricityUsageByIdAndDate(
            @Param("id") Long id,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as weekStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.id = :id " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY WEEK(e.at) " +
            "ORDER BY weekStart DESC")
    List<Object[]> findWeeklyAverageElectricityUsageById(
            @Param("id") Long id,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as monthStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE u.id = :id " +
            "AND e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY YEAR(e.at), MONTH(e.at) " +
            "ORDER BY monthStart DESC")
    List<Object[]> findMonthlyAverageElectricityUsageById(
            @Param("id") Long id,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
    /////////////

    // 시 평균 전력 사용량
    @Query("SELECT DATE(e.at), AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(e.at) " +
            "ORDER BY DATE(e.at) DESC")
    List<Object[]> findAverageElectricityUsageByDate(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as weekStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY WEEK(e.at) " +
            "ORDER BY weekStart DESC")
    List<Object[]> findWeeklyAverageElectricityUsage(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT MIN(e.at) as monthStart, AVG(e.elec) FROM Electricity e " +
            "JOIN e.user u " +
            "WHERE e.at BETWEEN :startDate AND :endDate " +
            "GROUP BY YEAR(e.at), MONTH(e.at) " +
            "ORDER BY monthStart DESC")
    List<Object[]> findMonthlyAverageElectricityUsage(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
