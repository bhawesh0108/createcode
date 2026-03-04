package com.bhawesh.createCode.entity;

import com.bhawesh.createCode.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    Long id;
    String content;
    MessageRole role;
    ChatSession chatSession;
    String toolCalls;
    Integer tokensUsed;
    Instant createdAt;
}
