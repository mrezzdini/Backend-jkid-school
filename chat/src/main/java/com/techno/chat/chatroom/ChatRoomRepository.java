package com.techno.chat.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findById(String id);

    List<ChatRoom> findByUsersId(String userId);
}
