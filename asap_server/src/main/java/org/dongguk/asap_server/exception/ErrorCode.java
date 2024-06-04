package org.dongguk.asap_server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //Bad Request Error
    INVALID_PARAMETER("40000", HttpStatus.BAD_REQUEST, "유효하지 않는 파라미터입니다."),
    MISSING_REQUEST_PARAMETER("40001", HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),
    INVALID_HEADER("40002", HttpStatus.BAD_REQUEST, "유효하지 않은 헤더값입니다."),
    MISSING_REQUEST_BODY("40003", HttpStatus.BAD_REQUEST, "요청 바디가 누락되었습니다."),

    // Unauthorized Error
    FAILURE_LOGIN("40100", HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다."),

    // Access Denied Error
    ACCESS_DENIED_ERROR("40300", HttpStatus.FORBIDDEN, "액세스 권한이 없습니다."),

    // Not Found Error
    NOT_FOUND_USER("40400", HttpStatus.NOT_FOUND, "해당 사용자가 존재하지 않습니다."),
    NOT_FOUND_END_POINT("40401", HttpStatus.NOT_FOUND, "존재하지 않는 엔드포인트입니다."),
    NOT_FOUND_RESOURCE("40402", HttpStatus.NOT_FOUND, "요청한 데이터를 찾을 수 없습니다."),

    // UnsupportedMediaType Error
    UNSUPPORTED_MEDIA_TYPE("41500", HttpStatus.UNSUPPORTED_MEDIA_TYPE, "허용되지 않은 파일 형식입니다."),

    // Server, File Up/DownLoad Error
    SERVER_ERROR("50000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
