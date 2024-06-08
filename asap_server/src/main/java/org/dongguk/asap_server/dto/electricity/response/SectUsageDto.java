package org.dongguk.asap_server.dto.electricity.response;

import lombok.Builder;
import org.dongguk.asap_server.domain.Electricity;
import org.dongguk.asap_server.domain.User;
import org.dongguk.asap_server.dto.user.response.StatusDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record SectUsageDto(
        String time,
        Double elec
) {
    public static List<SectUsageDto> fromEntityList(List<Object[]> electricities){
        List<SectUsageDto> dtoList = new ArrayList<>();

        for(Object row[] : electricities){
            LocalDateTime time = (LocalDateTime) row[0];
            Double elec = (Double) row[1];
            SectUsageDto statusDto =
                    SectUsageDto.builder()
                            .time(time.toString())
                            .elec(elec)
                            .build();

            dtoList.add(statusDto);
        }

        return dtoList;
    }
}
