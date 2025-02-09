package com.techno.chat.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
@CrossOrigin(origins = "http://gcs-conf.com")
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<ChatMessage>> findAllChatMessages(){
        return ResponseEntity
                .ok(messageService.findAllChatMessages());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String roomId ){
        return ResponseEntity
                .ok(messageService.findMessagesByRoomId(roomId));
    }

    @DeleteMapping(path = "{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable("messageId") String messageId){
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok("message deleted successfully");

    }
}
