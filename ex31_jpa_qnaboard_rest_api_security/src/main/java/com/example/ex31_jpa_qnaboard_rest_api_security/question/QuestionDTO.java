package com.example.ex31_jpa_qnaboard_rest_api_security.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {

    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200, message="제목은 200자를 넘을 수 없습니다.")
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
    
}