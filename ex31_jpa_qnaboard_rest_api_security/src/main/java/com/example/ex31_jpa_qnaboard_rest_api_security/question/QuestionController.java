package com.example.ex31_jpa_qnaboard_rest_api_security.question;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

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
    public ResponseEntity<String> questionCreate(@Valid @RequestBody QuestionDTO questionDTO, BindingResult bindingResult) {

        // 유효성 검사 실패 시 에러 메시지 반환
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> errorMsg.append(error.getDefaultMessage()).append("; "));
            return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            this.questionService.create(questionDTO.getSubject(), questionDTO.getContent());
            return new ResponseEntity<>("Question created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed create question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
