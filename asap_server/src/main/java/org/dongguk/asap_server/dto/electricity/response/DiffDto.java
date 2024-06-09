package org.dongguk.asap_server.dto.electricity.response;

import lombok.Builder;

@Builder
public record DiffDto(
        Double diff
) {
    public static DiffDto fromDiff(double diff){
        return DiffDto.builder()
                .diff(diff)
                .build();
    }
}
