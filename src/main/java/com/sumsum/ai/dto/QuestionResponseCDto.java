package com.sumsum.ai.dto;

import com.sumsum.ai.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class QuestionResponseCDto {

    private Long id;

    private String question;

    private String answer;

    private String charMo;

    public QuestionResponseCDto(Question question1){
        // constructor by using entity
        this.id = question1.getId();
        this.question = question1.getQuestion();
        this.answer = question1.getAnswer();
        this.charMo = question1.getCharMo();
    }

}
