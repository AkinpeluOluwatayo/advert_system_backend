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
@CrossOrigin(origins = "https://dealbridgeconnect.netlify.app", allowCredentials = "true")
@RequestMapping("/chat")
public class ChatController {

    private final ChatServicesInterface chatService;

    @Autowired
    public ChatController(ChatServicesInterface chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ChatsResponse>> createChat(
            @RequestParam("userId") String userId,
            @RequestBody ChatsRequests request
    ) {
        ChatsResponse response = chatService.createChat(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, response));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ChatsResponse>>> getChatsByUser(@PathVariable("userId") String userId) {
        List<ChatsResponse> responses = chatService.getChatsByUser(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, responses));
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ApiResponse<ChatsResponse>> getChatById(@PathVariable("chatId") String chatId) {
        ChatsResponse response = chatService.getChatById(chatId);
        return ResponseEntity.ok(new ApiResponse<>(true, response));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteChatsByUser(@RequestParam("userId") String userId) {
        chatService.deleteChat(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "All chats for the user deleted successfully"));
    }

}
