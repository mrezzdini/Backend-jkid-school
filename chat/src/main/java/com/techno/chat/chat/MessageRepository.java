package com.techno.chat.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findAllByRoomId(String roomId);

    void deleteAllByRoomId(String roomId);
}
