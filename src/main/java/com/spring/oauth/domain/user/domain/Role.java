package com.spring.oauth.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    MENTEE("ROLE_MENTEE", "멘티"),
    MENTOR("ROLE_MENTOR", "멘토");

    private final String key;
    private final String title;
}
