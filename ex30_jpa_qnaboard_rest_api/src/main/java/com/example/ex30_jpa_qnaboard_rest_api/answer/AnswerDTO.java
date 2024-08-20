package com.example.ex30_jpa_qnaboard_rest_api.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

    @NotEmpty(message="답변 내용은 필수항목입니다.")
    private String content;
    
}
