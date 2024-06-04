package org.dongguk.asap_server.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EStatus {
    NOMAL("NOMAL"),
    WARN("WARN"),
    DANG("DANG");

    private final String status;
}
