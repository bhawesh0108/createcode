package com.bhawesh.createCode.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsageLog {

    Long id;
    User user;
    Project project;
    Integer tokensUsed;
    String metaData;
    Integer durationMs;
    String action;
    Instant createdAt;
}
