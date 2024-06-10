package org.dongguk.asap_server.dto.user.response;

import lombok.Builder;
import org.dongguk.asap_server.domain.User;

import java.util.List;

@Builder
public record SectStatusDto(
        Integer normal,
        Integer warn,
        Integer dang
) {
    public static SectStatusDto fromEntityList(List<User> users) {
        Integer normal=0, warn=0, dang=0;

        for (User user : users) {
            switch (user.getStatus()){
                case NORMAL:
                    normal++;
                    break;
                case WARN:
                    warn++;
                    break;
                case DANG:
                    dang++;
                    break;
            }
        }

        return SectStatusDto.builder()
                .normal(normal)
                .warn(warn)
                .dang(dang)
                .build();
    }
}
