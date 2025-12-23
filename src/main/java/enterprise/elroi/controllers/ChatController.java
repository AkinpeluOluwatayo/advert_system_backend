package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.ChatsRequests;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.dto.responses.ChatsResponse;
import enterprise.elroi.services.chatServices.ChatServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatServicesInterface chatService;

    @Autowired
    public ChatController(ChatServicesInterface chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChat(@RequestBody ChatsRequests request, @RequestParam String userId) {
        try {
            ChatsResponse response = chatService.createChat(request, userId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getChatsByUser(@PathVariable String userId) {
        try {
            List<ChatsResponse> responses = chatService.getChatsByUser(userId);
            return new ResponseEntity<>(new ApiResponse(true, responses), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<?> getChatById(@PathVariable String chatId) {
        try {
            ChatsResponse response = chatService.getChatById(chatId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable String chatId, @RequestParam String userId) {
        try {
            chatService.deleteChat(chatId, userId);
            return new ResponseEntity<>(new ApiResponse(true, "Chat deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
