package com.sumsum.ai.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sumsum.ai.dto.QuestionRequestDto;
import com.sumsum.ai.dto.QuestionResponseCDto;
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
    public ResponseEntity<QuestionResponseDto> questionPost(@RequestBody QuestionRequestDto dto) throws JsonProcessingException {
        // create question and get answer
        return ResponseEntity.status(HttpStatus.OK).body(service.postQuestion(dto));
    }

    @PostMapping("/question/post/{charNum}")
    public ResponseEntity<QuestionResponseCDto> questionPostCharacter(@PathVariable int charNum ,
                                                                          @RequestBody QuestionRequestDto dto) throws JsonProcessingException {
        // create question, set character and get answer
        return ResponseEntity.status(HttpStatus.OK).body(service.postCharacterQuestion(charNum,dto));
    }

    @GetMapping("/question/{author}")
    public ResponseEntity<List<QuestionResponseDto>> queByAuthor(@PathVariable String author){
        // get all question by author
        return ResponseEntity.status(HttpStatus.OK).body(service.getQuestion(author));
    }

    @GetMapping("/question/{author}/{charMo}")
    public ResponseEntity<List<QuestionResponseCDto>> queByAuthorChar(@PathVariable String author, @PathVariable String charMo){
        // get question and answer list by char and author
        return ResponseEntity.status(HttpStatus.OK).body(service.getQByCAuthor(author,charMo));
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Long> deleteQue(@PathVariable Long id){
        // delete question by id
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteQue(id));
    }

}
