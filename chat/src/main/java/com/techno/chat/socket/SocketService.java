package com.techno.chat.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.techno.chat.chat.ChatMessage;
import com.techno.chat.chat.MessageService;
import com.techno.chat.chatroom.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocketService {

    private final MessageService messageService;

    public void sendSocketMessage(SocketIOClient senderClient, ChatMessage message, String room) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("read_message",
                        message);
                client.sendEvent("read_conversation",message);
            }
        }
    }

    public void saveMessage(SocketIOClient senderClient, ChatMessage message) {
        ChatMessage storedMessage = messageService.saveMessage(message);
        sendSocketMessage(senderClient, storedMessage, message.getRoomId());
    }

    public void conversationDelete(SocketIOClient senderClient, ChatRoom data) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(data.getId()).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {

                client.sendEvent("conversation_deleted",data);
            }
        }
    }

    public void UserConversationUpdate(SocketIOClient senderClient, ChatRoom data, Collection<SocketIOClient> allClients) {
        for (
                SocketIOClient client : allClients) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("user_conversation_updated",data);
            }
        }
    }

    public void conversationCreate(SocketIOClient senderClient, ChatRoom data, Collection<SocketIOClient> allClients) {
        for (
                SocketIOClient client : allClients) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("conversation_created",data);
            }
        }
    }
}