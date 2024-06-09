package org.dongguk.asap_server.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record PagingResponseDto<T>(
        @Nullable T items,
        @Nullable PageInfoDto pageInfo
) {
    public static <T> PagingResponseDto<T> fromEntityAndPageInfo(T data, PageInfoDto pageInfoDto){
        return new PagingResponseDto<T>(data, pageInfoDto);
    }
}
