package com.sumsum.ai.dto;

import com.sumsum.ai.entity.Question;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class QuestionRequestDto {

    public String author;

    public String question;

    public Question toQEntity(){
        return Question.builder()
                .author(author)
                .question(question)
                .build();
    }
}
