package com.gpt.controller;

import com.gpt.dto.ChatGPTRequest;
import com.gpt.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/chat")
public class GPTController {

    @Value("${openai.model}")
    private String model;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.url}")
    private String url;

    @GetMapping("/ask")
    public ResponseEntity<Map<String,String>> chat(@RequestParam("prompt") String prompt ){
        ChatGPTRequest request = new ChatGPTRequest(model,prompt);
        ChatGPTResponse response = restTemplate.postForObject(url,request, ChatGPTResponse.class);
        String res = response.getChoices().get(0).getMessage().getContent();

        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("response", res);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/check")
    public ResponseEntity<Map<String,String>> check(){
        Map<String, String> response = new HashMap<>();
        response.put("message", "response from check method");

        return ResponseEntity.ok().body(response);
    }



}
