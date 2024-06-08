package org.dongguk.asap_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.asap_server.domain.Electricity;
import org.dongguk.asap_server.dto.electricity.response.SectUsageDto;
import org.dongguk.asap_server.repository.ElectricityRepository;
import org.dongguk.asap_server.type.EDuration;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectricityService {
    private final ElectricityRepository electricityRepository;

    public List<SectUsageDto> readSectUsage(String sect, EDuration filt) {
        //5일간의 각 section의 평균 데이터
        switch (filt){
            case DAY :
                LocalDateTime endDate = LocalDate.now().atStartOfDay(); // 오늘 00:00
                LocalDateTime startDate = endDate.minusDays(5); // 4일 전 00:00
                List<Object[]> electricities = electricityRepository.findAverageElectricityUsageBySectionAndDate(sect, startDate, endDate);
                return SectUsageDto.fromEntityList(electricities);
        }


        //5주간의 각 week의 평균 데이터

        //5달간의 각 month의 평균 데이터
    }
}
