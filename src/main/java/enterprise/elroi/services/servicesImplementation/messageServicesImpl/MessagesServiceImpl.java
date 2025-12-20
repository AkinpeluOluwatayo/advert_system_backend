package enterprise.elroi.services.servicesImplementation.messageServicesImpl;

import enterprise.elroi.data.models.Messages;
import enterprise.elroi.data.repositories.MessagesRepository;
import enterprise.elroi.dto.requests.MessagesRequests;
import enterprise.elroi.dto.responses.MessagesResponse;
import enterprise.elroi.services.messageServices.MessagesServicesInterface;
import enterprise.elroi.util.mapper.messageMapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesServicesInterface {

    private final MessagesRepository repository;
    private final MessageMapper mapper;

    @Autowired
    public MessagesServiceImpl(MessagesRepository repository, MessageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public MessagesResponse sendMessage(MessagesRequests request, String senderId) {
        Messages message = new Messages();
        message.setChatId(request.getChatId());
        message.setSenderId(senderId);
        message.setContent(request.getContent());
        message.setCreatedAt(LocalDateTime.now());

        Messages savedMessage = repository.save(message);
        return mapper.toResponse(savedMessage);
    }

    @Override
    public List<MessagesResponse> getMessagesByChat(String chatId) {
        List<Messages> messagesList = repository.findAll();
        List<MessagesResponse> responses = new ArrayList<>();

        for (Messages message : messagesList) {
            if (message.getChatId().equals(chatId)) {
                responses.add(mapper.toResponse(message));
            }
        }
        return responses;
    }
}