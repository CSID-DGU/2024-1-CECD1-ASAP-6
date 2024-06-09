package org.dongguk.asap_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.dongguk.asap_server.domain.User;
import org.dongguk.asap_server.dto.common.PageInfoDto;
import org.dongguk.asap_server.dto.common.PagingResponseDto;
import org.dongguk.asap_server.dto.user.request.RescueRequestDto;
import org.dongguk.asap_server.dto.user.response.RescueDto;
import org.dongguk.asap_server.dto.user.response.SearchHouseDto;
import org.dongguk.asap_server.dto.user.response.StatusDto;
import org.dongguk.asap_server.exception.CommonException;
import org.dongguk.asap_server.exception.ErrorCode;
import org.dongguk.asap_server.repository.UserRepository;
import org.dongguk.asap_server.type.EStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${nurigo.api-secret-key}")
    private String apiSecretKey;
    @Value("${nurigo.api-key}")
    private String apiKey;

    private final UserRepository userRepository;

    public PagingResponseDto<?> readRealtimeStatus(String sect, int page, int size) {
        Page<User> users = userRepository.findAllBySectionAndEditedAtNotNull(PageRequest.of(page, size), sect);

        PageInfoDto pageInfoDto = PageInfoDto.fromPageInfo(users);
        List<StatusDto> statusDtos = StatusDto.fromEntityList(users.getContent());

        return PagingResponseDto.fromEntityAndPageInfo(statusDtos, pageInfoDto);
    }

    public PagingResponseDto<?> searchHouseStatus(String sect, EStatus filt, String text,
                                             int page, int size){
        //만약 filt가 ALL이면 필터 없이
        if(filt == EStatus.ALL) filt = null;

        Page<User> users = userRepository.findAllBySectionAndStatusWithAddress(PageRequest.of(page, size), filt, sect, text);

        PageInfoDto pageInfoDto = PageInfoDto.fromPageInfo(users);
        List<SearchHouseDto> searchHouseDtos = SearchHouseDto.fromEntityList(users.getContent());

        return PagingResponseDto.fromEntityAndPageInfo(searchHouseDtos, pageInfoDto);
    }

    public RescueDto rescue(RescueRequestDto rescueRequestDto){
        User user = userRepository.findById(rescueRequestDto.id())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        String address = user.getAddress();
        String phone = "01024331103";
        String msg = "전력사용량 기반 이상탐지 시스템 ASAP입니다.\n" + address + " 에서 이상상황을 탐지했습니다.\n"
                + "신속한 조치가 필요합니다.";

        DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");

        Message message = new Message();
        message.setFrom("01024331103");
        message.setTo(phone);
        message.setText(msg);

        try {
            // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
            messageService.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return RescueDto.builder()
                .receiverPhone(phone)
                .message(msg)
                .build();
    }
}
