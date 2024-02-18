package com.khachidze.ChatGPT.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@Builder
public class UserModel {
    @Id
    private String id;
    private Long chatId;
    private List<String> messages;

}