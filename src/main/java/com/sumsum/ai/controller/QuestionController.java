package com.sumsum.ai.controller;


import com.sumsum.ai.dto.QuestionRequestDto;
import com.sumsum.ai.dto.QuestionResponseDto;
import com.sumsum.ai.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestionController {

    private final QuestionService service;

    @PostMapping("/question")
    public ResponseEntity<Long> questionPost(@RequestBody QuestionRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.postQuestion(dto));
    }

    @GetMapping("/question/{author}")
    public ResponseEntity<List<QuestionResponseDto>> queByAuthor(@PathVariable String author){
        return ResponseEntity.status(HttpStatus.OK).body(service.getQuestion(author));
    }

}
