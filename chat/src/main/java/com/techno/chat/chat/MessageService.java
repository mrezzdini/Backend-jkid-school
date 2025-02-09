package com.techno.chat.chat;

import com.techno.chat.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class MessageService {

        private final MessageRepository messageRepository;
        private final ChatRoomService chatRoomService;

        public ChatMessage saveMessage(ChatMessage message) {
            ChatMessage savedMessage = messageRepository.save(message);
            chatRoomService.SaveUpdatedChatRoom(savedMessage.getRoomId(), savedMessage);
            return savedMessage;
        }
        public List<ChatMessage> findAllChatMessages() {
            return messageRepository.findAll();
        }

        public List<ChatMessage> findMessagesByRoomId(String roomId) {
            return messageRepository.findAllByRoomId(roomId);
        }

        public void deleteMessage(String messageId) {
            messageRepository.deleteById(messageId);

        }
}