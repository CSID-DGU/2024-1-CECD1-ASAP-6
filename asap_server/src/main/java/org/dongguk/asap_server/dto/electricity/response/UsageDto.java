package org.dongguk.asap_server.dto.electricity.response;

import lombok.Builder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record UsageDto(
        String time,
        Double elec
) {
    public static List<UsageDto> fromEntityList(List<Object[]> electricities) {
        List<UsageDto> dtoList = new ArrayList<>();

        for(Object row[] : electricities){
            LocalDateTime dateTime;

            // 다양한 날짜 타입 처리
            if (row[0] instanceof Timestamp) {
                dateTime = ((Timestamp) row[0]).toLocalDateTime();
            } else if (row[0] instanceof Date) {
                dateTime = ((Date) row[0]).toLocalDate().atStartOfDay();
            } else if (row[0] instanceof LocalDateTime) {
                dateTime = (LocalDateTime) row[0];
            } else {
                throw new IllegalArgumentException("Unsupported date type: " + row[0].getClass().getName());
            }

            Double elec = (Double) row[1];
            UsageDto usageDto =
                    UsageDto.builder()
                            .time(dateTime.toString())
                            .elec(elec)
                            .build();

            dtoList.add(usageDto);
        }

        return dtoList.subList(0,6);
    }
}
