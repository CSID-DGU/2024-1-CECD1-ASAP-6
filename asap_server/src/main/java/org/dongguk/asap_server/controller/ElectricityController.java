package org.dongguk.asap_server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.asap_server.dto.common.ResponseDto;
import org.dongguk.asap_server.service.ElectricityService;
import org.dongguk.asap_server.type.EDuration;
import org.dongguk.asap_server.type.EStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ElectricityController {
    private final ElectricityService electricityService;
    @GetMapping("/sect/usage")
    public ResponseDto<?> readRealTimeStatus(@RequestParam("sect") String sect,
                                             @RequestParam("filt") EDuration filt){
        return ResponseDto.ok(electricityService.readSectUsage(sect,filt));
    }
}
