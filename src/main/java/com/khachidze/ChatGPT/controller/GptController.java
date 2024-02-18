package com.khachidze.ChatGPT.controller;


import com.khachidze.ChatGPT.dto.QuestionDto;
import com.khachidze.ChatGPT.service.GptService;
import com.khachidze.ChatGPT.service.YandexGptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GptController {

    private final GptService gptService;

    public GptController(GptService gptService) {
        this.gptService = gptService;
    }

    @PostMapping("/generate")
    public String generateResponse(@RequestBody QuestionDto questionDto) {
        return gptService.generateResponse(questionDto.getChatId(), questionDto.getQuestion());
    }
}