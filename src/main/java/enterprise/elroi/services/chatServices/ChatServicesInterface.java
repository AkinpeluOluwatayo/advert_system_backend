package enterprise.elroi.services.chatServices;

import enterprise.elroi.dto.requests.ChatsRequests;
import enterprise.elroi.dto.responses.ChatsResponse;

import java.util.List;

public interface ChatServicesInterface {
    ChatsResponse createChat(ChatsRequests request, String userId);


    List<ChatsResponse> getChatsByUser(String userId);

    ChatsResponse getChatById(String chatId);


    void deleteChat(String chatId);

}
