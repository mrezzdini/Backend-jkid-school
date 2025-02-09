package com.techno.chat.chatroom;

import com.techno.chat.chat.ChatMessage;
import com.techno.chat.chat.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> getAllChatRoomsByUserId(String userId) {
        return chatRoomRepository.findByUsersId(userId);
    }

    public void SaveUpdatedChatRoom(String roomId, ChatMessage message){
        Optional<ChatRoom> chatRoom=chatRoomRepository.findById(roomId);
        if (chatRoom.isPresent()){
            ChatRoom updatedChatRoom=chatRoom.get();
            updatedChatRoom.setLastMessageDate(message.getCreatedAt());
            updatedChatRoom.setLastMessage(message.getMessage());
            chatRoomRepository.save(updatedChatRoom);
        }
    }

    public void deleteChatRoom(String roomId) {
        messageRepository.deleteAllByRoomId(roomId);
        chatRoomRepository.deleteById(roomId);
    }
}