package com.example.ex31_jpa_qnaboard_rest_api_security.answer;

import java.time.LocalDateTime;

import com.example.ex31_jpa_qnaboard_rest_api_security.question.Question;
import com.example.ex31_jpa_qnaboard_rest_api_security.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "답변 내용은 필수 항목입니다.")
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    @JsonIgnore // 직열화 순환 참조 방지
    private Question question;

    @ManyToOne
    private UserEntity author;

    private LocalDateTime modifyDate;
}