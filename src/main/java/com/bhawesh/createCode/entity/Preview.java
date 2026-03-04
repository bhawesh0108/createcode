package com.bhawesh.createCode.entity;


import com.bhawesh.createCode.enums.PreviewStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {
    Long id;
    Project project;
    PreviewStatus status;
    String previewUrl;
    String nameSpace;
    String podName;
    Instant startedAt;
    Instant terminatedAt;
    Instant createdAt;

}
