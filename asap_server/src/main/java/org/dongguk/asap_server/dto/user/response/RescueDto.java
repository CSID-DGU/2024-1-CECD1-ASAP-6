package org.dongguk.asap_server.dto.user.response;

import lombok.Builder;

@Builder
public record RescueDto(
        String receiverPhone,
        String message
) {
}
