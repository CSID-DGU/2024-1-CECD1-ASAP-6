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

    @GetMapping("/house/usage")
    public ResponseDto<?> readHouseUsage(@RequestParam("id") Long id,
                                             @RequestParam("filt") EDuration filt){
        return ResponseDto.ok(electricityService.readHouseUsage(id, filt));
    }

    @GetMapping("/met/usage")
    public ResponseDto<?> readMetUsage(@RequestParam("filt") EDuration filt){
        return ResponseDto.ok(electricityService.readMetUsage(filt));
    }

    @GetMapping("/met/over")
    public ResponseDto<?> readMetOver(@RequestParam("id") Long id,
                                      @RequestParam("filt") EDuration filt){
        return ResponseDto.ok(electricityService.readMetOver(id, filt));
    } // 시 대비 평균 사용 초과량
}
