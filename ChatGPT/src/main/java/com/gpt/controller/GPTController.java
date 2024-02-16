package com.gpt.controller;

import com.gpt.dto.ChatGPTRequest;
import com.gpt.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/chat")
public class GPTController {

    @Value("${openai.model}")
    private String model;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.url}")
    private String url;

    @GetMapping("/ask")
    public String chat(@RequestParam("prompt") String prompt ){
        ChatGPTRequest request = new ChatGPTRequest(model,prompt);
        ChatGPTResponse response = restTemplate.postForObject(url,request, ChatGPTResponse.class);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
