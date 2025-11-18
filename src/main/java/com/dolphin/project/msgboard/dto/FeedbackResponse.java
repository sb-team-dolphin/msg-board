package com.dolphin.project.msgboard.dto;

import com.dolphin.project.msgboard.entity.Feedback;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedbackResponse {

    private Long id;
    private String username;
    private String message;
    private LocalDateTime createdAt;

    public static FeedbackResponse from(Feedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .username(feedback.getUsername() != null ? feedback.getUsername() : "익명")
                .message(feedback.getMessage())
                .createdAt(feedback.getCreatedAt())
                .build();
    }
}
