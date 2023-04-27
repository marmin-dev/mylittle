package com.sumsum.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumsum.ai.dto.QuestionRequestDto;
import com.sumsum.ai.dto.QuestionResponseDto;

import com.sumsum.ai.entity.Question;
import com.sumsum.ai.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Value("${api.key}")
    private String apiKey;

    String endpoint = "https://api.openai.com/v1/chat/completions";
    int maxToken = 5;


    public String createAnswer(String question) throws JsonProcessingException {
        // set header and body and request to open ai api
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        // set header properties
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(apiKey);
        // set body properties
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("model", "gpt-3.5-turbo");

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", question);

        List<Map<String, Object>> messages = new ArrayList<>();
        messages.add(message);

        requestBodyMap.put("messages", messages);
        requestBodyMap.put("temperature",1.5f);
        // parsing to String
        String requestBody = objectMapper.writeValueAsString(requestBodyMap);
        // set Http Entity
        HttpEntity<String> http = new HttpEntity<>(requestBody,httpHeaders);
        ResponseEntity entity = restTemplate.postForEntity(endpoint,http,String.class);
        // get response
        String response = entity.getBody().toString();
        //System.out.println(response);
        return response;
    }

    public String getContent(String data) throws JsonProcessingException {
        // get content data from openai Response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(data);
        String content = rootNode.get("choices").get(0).get("message").get("content").asText();
        return content;
    }


    @Transactional
    public QuestionResponseDto postQuestion(QuestionRequestDto dto) throws JsonProcessingException {
        // post question and get answer
        Question question = dto.toQEntity();
        String data = createAnswer(dto.getQuestion());
        String answer = getContent(data);
        question.setAnswer(answer);
        QuestionResponseDto responseDto = new QuestionResponseDto(question);
        System.out.println(answer);
        responseDto.setId(questionRepository.save(question).getId());
        return responseDto;
    }

    @Transactional
    public List<QuestionResponseDto> getQuestion(String author){
        // find all by author
        List<QuestionResponseDto> qList = questionRepository.findByAuthor(author, Sort.by(Sort.Order.desc("id")))
                .stream().map(question -> new QuestionResponseDto(question)).collect(Collectors.toList());
        return qList;
    }

    

}
