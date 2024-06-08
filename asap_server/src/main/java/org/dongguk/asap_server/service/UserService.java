package org.dongguk.asap_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.asap_server.domain.User;
import org.dongguk.asap_server.dto.common.PageInfoDto;
import org.dongguk.asap_server.dto.common.PagingResponseDto;
import org.dongguk.asap_server.dto.user.response.StatusDto;
import org.dongguk.asap_server.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public PagingResponseDto<?> readRealtimeStatus(String sect, int page, int size) {
        Page<User> users = userRepository.findAllBySectionAndEditedAtNotNull(PageRequest.of(page, size), sect);

        PageInfoDto pageInfoDto = PageInfoDto.fromPageInfo(users);
        List<StatusDto> statusDtos = StatusDto.fromEntityList(users.getContent());

        return PagingResponseDto.fromEntityAndPageInfo(statusDtos, pageInfoDto);
    }

    public PagingResponseDto<?> searchStatus(String sect, int page, int size){

    }
}
