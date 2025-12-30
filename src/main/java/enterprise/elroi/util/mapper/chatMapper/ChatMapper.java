package enterprise.elroi.util.mapper.chatMapper;

import enterprise.elroi.data.models.Chats;
import enterprise.elroi.dto.responses.ChatsResponse;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {

    public ChatsResponse toResponse(Chats chat) {
        if (chat == null) return null;

        ChatsResponse response = new ChatsResponse();
        response.setAdId(null);
        response.setParticipantId(chat.getParticipantId());
        response.setLastMessage(chat.getLastMessage());
        response.setUpdatedAt(chat.getUpdatedAt());

        return response;
    }
}