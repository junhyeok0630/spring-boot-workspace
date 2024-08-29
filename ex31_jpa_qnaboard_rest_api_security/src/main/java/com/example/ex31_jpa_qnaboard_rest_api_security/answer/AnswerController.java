package com.example.ex31_jpa_qnaboard_rest_api_security.answer;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ex31_jpa_qnaboard_rest_api_security.question.Question;
import com.example.ex31_jpa_qnaboard_rest_api_security.question.QuestionService;
import com.example.ex31_jpa_qnaboard_rest_api_security.user.UserEntity;
import com.example.ex31_jpa_qnaboard_rest_api_security.user.UserService;

import jakarta.validation.Valid;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@RestController
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PostMapping("/create/{id}")
    public ResponseEntity<String> createAnswer(@PathVariable("id") Integer id, @Valid @RequestBody AnswerDTO answerDTO,
            BindingResult bindingResult, Principal principal) {

        // 유효성 검사 실패 시 에러 메시지 반환
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> errorMsg.append(error.getDefaultMessage()).append("; "));
            return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
        }
        
        try {
            Question question = this.questionService.getQuestion(id);
            UserEntity user = this.userService.getUser(principal.getName());
            this.answerService.create(question, answerDTO.getContent(), user);
            return new ResponseEntity<>("Answer created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create answer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/modify/{id}")
    public ResponseEntity<String> answerModify(@PathVariable("id") Integer id, @Valid @RequestBody AnswerDTO answerDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> errorMsg.append(error.getDefaultMessage()).append("; "));

            return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
        }
        
        Answer answer = this.answerService.getAnswer(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName()); // principal.getName() 대신 사용

        System.out.println("authentication.getName() >>> " + authentication.getName());

        if (!answer.getAuthor().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.answerService.modify(answer, answerDTO.getContent());
        return new ResponseEntity<>("Answer modified successfully", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> questionDelete(@PathVariable("id") Integer id) {

        Answer answer = this.answerService.getAnswer(id);

        // 현재 로그인된 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = Long.parseLong(authentication.getName()); // principal.getName() 대신 사용

        // 사용자의 ID가 같지 않으면 권한 없음
        if (!answer.getAuthor().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }

        // 질문 삭제
        this.answerService.delete(answer);
        
        // 삭제 성공 후 204 응답 코드 반환
        return new ResponseEntity<>("Answer deleted successfully", HttpStatus.NO_CONTENT);
    }
}