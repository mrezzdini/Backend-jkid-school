package com.techno.chat.chatroom;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "chatRooms")
public class ChatRoom {
    @Id
    private String id;
    private String roomName;
    private String roomType; // Class or Parent
    private List<MyChatUser> users;
    private String lastMessageDate;
    private String lastMessage;
}

