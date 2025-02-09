package com.techno.chat.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.techno.chat.chat.ChatMessage;
import com.techno.chat.chatroom.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class SocketModule {

    private final SocketIOServer server;
    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", ChatMessage.class, onChatReceived());
        server.addEventListener("create_conversation", ChatRoom.class,onConversationCreate());
        server.addEventListener("delete_conversation", ChatRoom.class,onConversationDelete());
        server.addEventListener("update_user_conversation", ChatRoom.class,onUserConversationUpdate());

    }

    private DataListener<ChatMessage> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.saveMessage(senderClient, data);
        };
    }

    private DataListener<ChatRoom> onConversationCreate() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.conversationCreate(senderClient,data,server.getAllClients());
        };
    }
    private DataListener<ChatRoom> onUserConversationUpdate() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.UserConversationUpdate(senderClient,data,server.getAllClients());
        };
    }

    private DataListener<ChatRoom> onConversationDelete() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.conversationDelete(senderClient,data);
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            String username = client.getHandshakeData().getHttpHeaders().getAsString("username");
            List<String> rooms = Arrays.asList(client.getHandshakeData().getHttpHeaders().getAsString("rooms").split(","));
            rooms.forEach(room -> {
                Set<String> joinedRooms = client.getAllRooms();
                if (!joinedRooms.contains(room)) {
                    client.joinRoom(room);
                }
                log.info("Socket ID[{}] - room[{}] - username [{}] Connected to chat module", client.getSessionId().toString(), room, username);
            });
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            String username = client.getHandshakeData().getHttpHeaders().getAsString("username");
            List<String> rooms = Arrays.asList(client.getHandshakeData().getHttpHeaders().getAsString("rooms").split(","));
            rooms.forEach(room -> {
                Set<String> joinedRooms = client.getAllRooms();
                if (joinedRooms.contains(room)) {
                    client.leaveRoom(room);
                }
                log.info("Socket ID[{}] - room[{}] - username [{}] disconnected from chat module", client.getSessionId().toString(), room, username);
            });
        };
    }
}