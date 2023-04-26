package com.sumsum.ai.service;

import com.sumsum.ai.dto.QuestionRequestDto;
import com.sumsum.ai.dto.QuestionResponseDto;
import com.sumsum.ai.entity.Question;
import com.sumsum.ai.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long postQuestion(QuestionRequestDto dto){
        return questionRepository.save(dto.toQEntity()).getId();
    }

    @Transactional
    public List<QuestionResponseDto> getQuestion(String author){
        List<QuestionResponseDto> qList = questionRepository.findByAuthor(author, Sort.by(Sort.Order.desc("id")))
                .stream().map(question -> new QuestionResponseDto(question)).collect(Collectors.toList());
        return qList;
    }

}
