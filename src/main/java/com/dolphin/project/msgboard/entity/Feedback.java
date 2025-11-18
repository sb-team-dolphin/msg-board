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
        // XSS 방지: 저장 전 HTML escape 처리
        this.username = XssUtil.clean(this.username);
        this.message = XssUtil.clean(this.message);
    }

    @Builder
    private Feedback(Long id, String username, String message, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.createdAt = createdAt;
    }
}
