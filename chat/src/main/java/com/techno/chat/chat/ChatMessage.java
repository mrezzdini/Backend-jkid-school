package com.techno.chat.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "messages")
public class ChatMessage {
    @Id
    private String id;
    private String message;
    private String createdAt;
    private String sendBy;
    private String roomId;
}
