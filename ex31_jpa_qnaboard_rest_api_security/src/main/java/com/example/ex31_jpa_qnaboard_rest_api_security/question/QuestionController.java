package com.example.ex31_jpa_qnaboard_rest_api_security.question;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ex31_jpa_qnaboard_rest_api_security.user.UserEntity;
import com.example.ex31_jpa_qnaboard_rest_api_security.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public Page<Question> list(@RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        return paging;
    }

    @GetMapping(value = "/detail/{id}")
    public Question detail(@PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        return question;
    }

    @PostMapping("/create")
    public ResponseEntity<String> questionCreate(@Valid @RequestBody QuestionDTO questionDTO, BindingResult bindingResult, Principal principal) {

        // 유효성 검사 실패 시 에러 메시지 반환
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> errorMsg.append(error.getDefaultMessage()).append("; "));
            return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            UserEntity user = this.userService.getUser(principal.getName());
            this.questionService.create(questionDTO.getSubject(), questionDTO.getContent(), user);
            return new ResponseEntity<>("Question created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed create question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/modify/{id}")
    public ResponseEntity<String> modifyQuestion(@PathVariable("id") Integer id,
            @Valid @RequestBody QuestionDTO questionDTO,
            BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {

            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> errorMsg.append(error.getDefaultMessage()).append("; "));

            return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
        }

        Question question = this.questionService.getQuestion(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = Long.parseLong(authentication.getName()); // principal.getName() 대신 사용

        System.out.println("question.getAuthor().getId() >>> " + question.getAuthor().getId());
        System.out.println("userId >>> " + userId);

        if (!question.getAuthor().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }

        this.questionService.modify(question, questionDTO.getSubject(), questionDTO.getContent());
        return new ResponseEntity<>("Question modified successfully", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> questionDelete(@PathVariable("id") Integer id) {

        Question question = this.questionService.getQuestion(id);

        // 현재 로그인된 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName()); // principal.getName() 대신 사용

        System.out.println(
                "question.getAuthor().getId().equals(userId) >>> " + question.getAuthor().getId().equals(userId));

        // 사용자의 ID가 같지 않으면 권한 없음
        if (!question.getAuthor().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }

        // 질문 삭제
        this.questionService.delete(question);

        // 삭제 성공 후 204 응답 코드 반환
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.NO_CONTENT);
    }
}
