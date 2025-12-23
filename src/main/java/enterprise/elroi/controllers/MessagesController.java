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
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesServicesInterface messageService;

    @Autowired
    public MessagesController(MessagesServicesInterface messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessagesRequests request,
                                         @RequestParam String senderId) {
        try {
            MessagesResponse response = messageService.sendMessage(request, senderId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?> getMessagesByChat(@PathVariable String chatId) {
        try {
            List<MessagesResponse> responses = messageService.getMessagesByChat(chatId);
            return new ResponseEntity<>(new ApiResponse(true, responses), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
