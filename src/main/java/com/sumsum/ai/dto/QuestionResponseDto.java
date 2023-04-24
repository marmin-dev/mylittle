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



}
