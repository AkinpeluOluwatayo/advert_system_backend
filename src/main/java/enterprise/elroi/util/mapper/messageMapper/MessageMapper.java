package enterprise.elroi.util.mapper.messageMapper;

import enterprise.elroi.data.models.Messages;
import enterprise.elroi.dto.responses.MessagesResponse;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessagesResponse toResponse(Messages message) {
        if (message == null) return null;

        MessagesResponse response = new MessagesResponse();
        response.setChatId(message.getChatId());
        response.setSenderId(message.getSenderId());
        response.setContent(message.getContent());
        response.setCreatedAt(message.getCreatedAt());

        return response;
    }
}
