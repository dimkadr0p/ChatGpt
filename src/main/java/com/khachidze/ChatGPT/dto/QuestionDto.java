package com.khachidze.ChatGPT.dto;

import lombok.Data;

@Data
public class QuestionDto {
    private Long chatId;
    private String question;
}
