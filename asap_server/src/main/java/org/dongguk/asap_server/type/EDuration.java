package org.dongguk.asap_server.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EDuration {
    DAY("DAY"),
    WEEK("WEEK"),
    MONTH("MONTH");

    private final String duration;
}
