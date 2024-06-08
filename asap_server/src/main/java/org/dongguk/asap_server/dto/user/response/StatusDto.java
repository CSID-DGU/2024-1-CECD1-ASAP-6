package org.dongguk.asap_server.dto.user.response;

import lombok.Builder;
import org.dongguk.asap_server.domain.User;
import org.dongguk.asap_server.type.EStatus;

import java.util.ArrayList;
import java.util.List;

@Builder
public record StatusDto(
        Long id,
        String editedAt,
        String address,
        EStatus lastStatus,
        EStatus status
) {
    public static List<StatusDto> fromEntityList(List<User> users){
        List<StatusDto> dtoList = new ArrayList<>();

        for(User user : users){
            StatusDto statusDto =
                    StatusDto.builder()
                            .id(user.getId())
                            .editedAt(user.getEditedAt().toString())
                            .address(user.getAddress())
                            .status(user.getStatus())
                            .lastStatus(user.getLastStatus())
                            .build();

            dtoList.add(statusDto);
        }

        return dtoList;
    }
}
