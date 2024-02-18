package com.khachidze.ChatGPT.service;

import com.khachidze.ChatGPT.config.YandexApiConfig;
import com.khachidze.ChatGPT.model.UserModel;
import com.khachidze.ChatGPT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class YandexGptService implements GptService {
    
    @Autowired
    private UserRepository userRepository;

    private static final String API_URL = "https://llm.api.cloud.yandex.net/foundationModels/v1/completion";

    @Override
    public String generateResponse(Long chatId, String question) {
        question = question.replace("\"", "\\\""); // Экранирование двойных кавычек
        question = question.replace("\n", "\\n"); // Экранирование символов пер

        UserModel user = userRepository.findByChatId(chatId).orElse(UserModel.builder()
                .chatId(chatId)
                .messages(new ArrayList<>())
                .build());

        List<String> messageHistory = user.getMessages();

        messageHistory.add(question);

        String fullMessageHistory = String.join(". ", messageHistory); // Объединение всех сообщений в одну строку

        HttpEntity<String> entity = getStringHttpEntity(fullMessageHistory);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        user.setMessages(messageHistory);

        userRepository.save(user);

        return response.getBody();
    }


    private HttpEntity<String> getStringHttpEntity(String fullMessageHistory) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + YandexApiConfig.API_KEY);
        headers.set("x-folder-id", YandexApiConfig.FOLDER_ID);

        String requestBody = "{\"modelUri\": \"gpt://" + YandexApiConfig.FOLDER_ID + "/yandexgpt-lite\", " +
                "\"completionOptions\": {\"stream\": false, \"temperature\": 0.6, \"maxTokens\": \"2000\"}, " +
                "\"messages\": [{\"role\": \"system\", \"text\": \"Generate response to the question\"}, " +
                "{\"role\": \"user\", \"text\": \"" + fullMessageHistory.replace("\n", "\\n") + "\"}]}";


        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        return entity;
    }
}


