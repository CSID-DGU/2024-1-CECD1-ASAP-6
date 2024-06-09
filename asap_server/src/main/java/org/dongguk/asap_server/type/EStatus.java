package org.dongguk.asap_server.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EStatus {
    ALL("ALL"),
    NORMAL("NORMAL"),
    WARN("WARN"),
    DANG("DANG");

    private final String status;
}
