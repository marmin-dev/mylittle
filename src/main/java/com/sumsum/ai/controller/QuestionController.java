package com.sumsum.ai.controller;


import com.sumsum.ai.dto.QuestionRequestDto;
import com.sumsum.ai.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestionController {

    private static QuestionService service;

    @PostMapping("/question")
    public ResponseEntity<Long> questionPost(@RequestBody QuestionRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.postQuestion(dto));
    }

}
