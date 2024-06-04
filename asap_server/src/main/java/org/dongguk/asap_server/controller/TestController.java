package org.dongguk.asap_server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.asap_server.dto.common.ResponseDto;
import org.dongguk.asap_server.exception.CommonException;
import org.dongguk.asap_server.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping("/error")
    public ResponseDto<?> testError(){
        throw new CommonException(ErrorCode.INVALID_PARAMETER);
    }

    @GetMapping("/hello")
    public ResponseDto<?> testHello(){
        return ResponseDto.ok("hello, world");
    }
}
