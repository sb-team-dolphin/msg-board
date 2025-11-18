package com.dolphin.project.msgboard.entity;

import com.dolphin.project.msgboard.util.XssUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String username;

    @Column(nullable = false, length = 1000)
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Builder
    private Feedback(Long id, String username, String message, LocalDateTime createdAt) {
        this.id = id;
        // XSS 방지: 생성 시점에 HTML escape 처리
        this.username = XssUtil.clean(username);
        this.message = XssUtil.clean(message);
        this.createdAt = createdAt;
    }
}
