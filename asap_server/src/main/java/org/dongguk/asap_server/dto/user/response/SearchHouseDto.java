package org.dongguk.asap_server.dto.user.response;

import lombok.Builder;
import org.dongguk.asap_server.domain.User;
import org.dongguk.asap_server.type.EStatus;

import java.util.ArrayList;
import java.util.List;

@Builder
public record SearchHouseDto(
        Long id,
        String address,
        EStatus status
) {
    public static List<SearchHouseDto> fromEntityList(List<User> users){
        List<SearchHouseDto> dtoList = new ArrayList<>();

        for(User user : users){
            SearchHouseDto statusDto =
                    SearchHouseDto.builder()
                            .id(user.getId())
                            .address(user.getAddress())
                            .status(user.getStatus())
                            .build();

            dtoList.add(statusDto);
        }

        return dtoList;
    }
}
