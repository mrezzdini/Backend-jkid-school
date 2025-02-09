package com.techno.chat.chatroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyChatUser {
    @Id
    private String id  ;
    private String name;
    private String profilePhoto;
    private String role;
}
