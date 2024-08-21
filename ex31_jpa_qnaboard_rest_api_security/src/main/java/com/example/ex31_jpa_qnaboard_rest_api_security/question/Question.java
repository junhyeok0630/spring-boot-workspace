package com.example.ex31_jpa_qnaboard_rest_api_security.question;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ex31_jpa_qnaboard_rest_api_security.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200, message="제목은 200자를 넘을 수 없습니다.")
    @Column(length = 200)
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) 
    private List<Answer> answerList;
}