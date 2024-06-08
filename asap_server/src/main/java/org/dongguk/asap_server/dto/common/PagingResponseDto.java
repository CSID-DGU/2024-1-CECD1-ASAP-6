package org.dongguk.asap_server.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public record PagingResponseDto<T>(
        @Nullable T items,
        @Nullable PageInfoDto pageInfo
) {
}
