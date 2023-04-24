package com.sumsum.ai.service;

import com.sumsum.ai.dto.QuestionRequestDto;
import com.sumsum.ai.entity.Question;
import com.sumsum.ai.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private static QuestionRepository questionRepository;

    @Transactional
    public Long postQuestion(QuestionRequestDto dto){
        Question que = dto.toQEntity();
        return questionRepository.save(que).getId();
    }


}
