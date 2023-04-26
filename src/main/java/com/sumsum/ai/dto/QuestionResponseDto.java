package com.sumsum.ai.dto;
import com.sumsum.ai.entity.Question;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class QuestionResponseDto {

    private Long id;

    private String question;

    private String answer;

    public QuestionResponseDto(Question question1){
        this.id = question1.getId();
        this.question = question1.getQuestion();
        this.answer = question1.getAnswer();
    }

}
