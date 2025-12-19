package enterprise.elroi.services.messageServices;

import enterprise.elroi.dto.requests.MessagesRequests;
import enterprise.elroi.dto.responses.MessagesResponse;

import java.util.List;

public interface MessagesServicesInterface {


    MessagesResponse sendMessage(MessagesRequests request, String senderId);

    List<MessagesResponse> getMessagesByChat(String chatId);
}
