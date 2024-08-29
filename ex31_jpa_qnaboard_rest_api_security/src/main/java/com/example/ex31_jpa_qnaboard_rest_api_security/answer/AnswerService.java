package com.example.ex31_jpa_qnaboard_rest_api_security.answer;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.ex31_jpa_qnaboard_rest_api_security.DataNotFoundException;
import com.example.ex31_jpa_qnaboard_rest_api_security.question.Question;
import com.example.ex31_jpa_qnaboard_rest_api_security.user.UserEntity;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Question question, String content, UserEntity author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }

    // ! 답변 조회 추가(조회 기능이 있어야 수정, 삭제 가능)
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);

        System.out.println("this.answerRepository.findById(id) >>> " + answer);

        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    // ! 답변 수정 추가
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    // ! 답변 삭제 추가
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }
}