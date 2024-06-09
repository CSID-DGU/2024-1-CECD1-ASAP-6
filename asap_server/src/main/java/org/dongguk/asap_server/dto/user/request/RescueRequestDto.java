package org.dongguk.asap_server.dto.user.request;

import jakarta.validation.constraints.NotNull;

public record RescueRequestDto(
        @NotNull
        Long id
) {
}
