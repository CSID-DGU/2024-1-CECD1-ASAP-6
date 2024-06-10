package org.dongguk.asap_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.asap_server.dto.electricity.response.DiffDto;
import org.dongguk.asap_server.dto.electricity.response.UsageDto;
import org.dongguk.asap_server.dto.electricity.response.SectUsageDto;
import org.dongguk.asap_server.repository.ElectricityRepository;
import org.dongguk.asap_server.type.EDuration;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
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
                endDate = LocalDate.of(2024, 6, 9).atStartOfDay().minusMinutes(1); // 오늘 00:00
                startDate = endDate.minusDays(5);
                electricities = electricityRepository.findAverageElectricityUsageBySectionAndDate(sect, startDate, endDate);
                return SectUsageDto.fromEntityList(electricities);
            case WEEK:
                today = LocalDate.of(2024, 6, 8).atStartOfDay();
                startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(4);
                endDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)); // 가장 최근 토요일

                electricities = electricityRepository.findWeeklyAverageElectricityUsageBySection(sect, startDate, endDate);
                return SectUsageDto.fromEntityList(electricities);
            case MONTH:
                endDate = LocalDate.of(2024, 6, 8).atStartOfDay(); // 오늘의 00:00
                startDate = endDate.minusMonths(4).withDayOfMonth(1);

                electricities = electricityRepository.findMonthlyAverageElectricityUsageBySection(sect, startDate, endDate);
                return SectUsageDto.fromEntityList(electricities);
        }

        log.info("empty list");
        return Collections.emptyList();
    }

    public List<UsageDto> readHouseUsage(Long id, EDuration filt) {
        List<Object[]> electricities;

        LocalDateTime startDate;
        LocalDateTime endDate;
        LocalDateTime today;

        switch (filt){
            case DAY :
                endDate = LocalDate.of(2024, 6, 9).atStartOfDay().minusMinutes(1); // 오늘 00:00
                startDate = endDate.minusDays(6);
                electricities = electricityRepository.findAverageElectricityUsageByIdAndDate(id, startDate, endDate);
                return UsageDto.fromEntityList(electricities);
            case WEEK:
                today = LocalDate.of(2024, 6, 8).atStartOfDay();
                startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(5);
                endDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)); // 가장 최근 토요일

                electricities = electricityRepository.findWeeklyAverageElectricityUsageById(id, startDate, endDate);
                return UsageDto.fromEntityList(electricities);
            case MONTH:
                endDate = LocalDate.of(2024, 6, 8).atStartOfDay(); // 오늘의 00:00
                startDate = endDate.minusMonths(5).withDayOfMonth(1);

                electricities = electricityRepository.findMonthlyAverageElectricityUsageById(id, startDate, endDate);
                return UsageDto.fromEntityList(electricities);
        }

        log.info("empty list");
        return Collections.emptyList();
    }

    public List<UsageDto> readMetUsage(EDuration filt) {
        List<Object[]> electricities;

        LocalDateTime startDate;
        LocalDateTime endDate;
        LocalDateTime today;

        switch (filt){
            case DAY :
                endDate = LocalDate.of(2024, 6, 9).atStartOfDay().minusMinutes(1); // 오늘 00:00
                startDate = endDate.minusDays(6);
                electricities = electricityRepository.findAverageElectricityUsageByDate(startDate, endDate);
                return UsageDto.fromEntityList(electricities);
            case WEEK:
                today = LocalDate.of(2024, 6, 8).atStartOfDay();
                startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(5);
                endDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)); // 가장 최근 토요일

                electricities = electricityRepository.findWeeklyAverageElectricityUsage(startDate, endDate);
                return UsageDto.fromEntityList(electricities);
            case MONTH:
                endDate = LocalDate.of(2024, 6, 8).atStartOfDay(); // 오늘의 00:00
                startDate = endDate.minusMonths(5).withDayOfMonth(1);

                electricities = electricityRepository.findMonthlyAverageElectricityUsage(startDate, endDate);
                return UsageDto.fromEntityList(electricities);
        }

        log.info("empty list");
        return Collections.emptyList();
    }

    public DiffDto readMetOver(Long id, EDuration filt) {
        List<Object[]> houseElec = null;
        List<Object[]> metElec = null;

        LocalDateTime startDate;
        LocalDateTime endDate;
        LocalDateTime today;

        switch (filt){
            case DAY :
                endDate = LocalDate.of(2024, 6, 9).atStartOfDay().minusMinutes(1); // 오늘 00:00
                startDate = endDate.minusDays(6);
                houseElec = electricityRepository.findAverageElectricityUsageByIdAndDate(id, startDate, endDate);
                metElec = electricityRepository.findAverageElectricityUsageByDate(startDate, endDate);
                break;
            case WEEK:
                today = LocalDate.of(2024, 6, 8).atStartOfDay();
                startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(5);
                endDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)); // 가장 최근 토요일

                houseElec = electricityRepository.findWeeklyAverageElectricityUsageById(id, startDate, endDate);
                metElec = electricityRepository.findWeeklyAverageElectricityUsage(startDate, endDate);
                break;
            case MONTH:
                endDate = LocalDate.of(2024, 6, 8).atStartOfDay(); // 오늘의 00:00
                startDate = endDate.minusMonths(5).withDayOfMonth(1);

                houseElec = electricityRepository.findMonthlyAverageElectricityUsageById(id, startDate, endDate);
                metElec = electricityRepository.findMonthlyAverageElectricityUsage(startDate, endDate);
                break;
        }

        double house = 0;
        double met = 0;
        for(int i = 0; i < houseElec.size(); i++){
            house += (Double) houseElec.get(i)[1];
            met += (Double) metElec.get(i)[1];
        }
        log.info(String.valueOf(house));
        log.info(String.valueOf(met));

        double diff = ((house - met) / house) * 100;

        return DiffDto.fromDiff(diff);
    } // 시 대비 평균 사용 초과량
}
