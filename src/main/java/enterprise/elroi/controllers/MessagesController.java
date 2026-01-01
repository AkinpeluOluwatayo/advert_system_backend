package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.MessagesRequests;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.dto.responses.MessagesResponse;
import enterprise.elroi.services.messageServices.MessagesServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesServicesInterface messageService;

    @Autowired
    public MessagesController(MessagesServicesInterface messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<MessagesResponse>> sendMessage(@RequestBody MessagesRequests request, @RequestParam("senderId") String senderId) {
        MessagesResponse response = messageService.sendMessage(request, senderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, response));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<ApiResponse<List<MessagesResponse>>> getMessagesByChat(@PathVariable("chatId") String chatId) {
        List<MessagesResponse> responses = messageService.getMessagesByChat(chatId);
        return ResponseEntity.ok(new ApiResponse<>(true, responses));
    }
}
