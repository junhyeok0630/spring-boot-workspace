package com.example.ex31_jpa_qnaboard_rest_api_security.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ex31_jpa_qnaboard_rest_api_security.question.Question;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
}