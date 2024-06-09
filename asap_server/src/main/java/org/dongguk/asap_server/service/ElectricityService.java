package org.dongguk.asap_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.asap_server.domain.Electricity;
import org.dongguk.asap_server.dto.electricity.response.SectUsageDto;
import org.dongguk.asap_server.repository.ElectricityRepository;
import org.dongguk.asap_server.type.EDuration;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectricityService {
    private final ElectricityRepository electricityRepository;

    public List<SectUsageDto> readSectUsage(String sect, EDuration filt) {
        List<Object[]> electricities;

        LocalDateTime startDate;
        LocalDateTime endDate;
        LocalDateTime today;

        switch (filt){
            case DAY :
                endDate = LocalDate.of(2024, 6, 8).atStartOfDay(); // 오늘 00:00
                startDate = endDate.minusDays(5); // 4일 전 00:00
                electricities = electricityRepository.findAverageElectricityUsageBySectionAndDate(sect, startDate, endDate);
                return SectUsageDto.fromEntityList(electricities);
            case WEEK:
                today = LocalDate.of(2024, 6, 8).atStartOfDay();
                startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(5); // 5주 전 일요일
                endDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)); // 가장 최근 토요일

                electricities = electricityRepository.findWeeklyAverageElectricityUsageBySection(sect, startDate, endDate);
                return SectUsageDto.fromEntityList(electricities);
            case MONTH:
                endDate = LocalDate.of(2024, 6, 8).atStartOfDay(); // 오늘의 00:00
                startDate = endDate.minusMonths(5).withDayOfMonth(1); // 5개월 전 첫 날

                electricities = electricityRepository.findMonthlyAverageElectricityUsageBySection(sect, startDate, endDate);
                return SectUsageDto.fromEntityList(electricities);
        }

        log.info("empty list");
        return Collections.emptyList();
    }
}
