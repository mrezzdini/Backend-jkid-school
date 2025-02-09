package com.techno.chat.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRooms")
@CrossOrigin(origins = "   ")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.createChatRoom(chatRoom);
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms(){
        return  ResponseEntity.ok( chatRoomService.getAllChatRooms());
    }

    @DeleteMapping(path = "{roomId}")
    public ResponseEntity<String> deleteChatRoom(@PathVariable("roomId") String roomId){
         chatRoomService.deleteChatRoom(roomId);
         return ResponseEntity.ok("chat Room deleted successfully");

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ChatRoom>> getAllChatRoomsByUser(@PathVariable String userId){
        return ResponseEntity.ok(chatRoomService.getAllChatRoomsByUserId(userId));
    }
}
