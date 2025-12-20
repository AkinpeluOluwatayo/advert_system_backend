package enterprise.elroi.services.servicesImplementation.chatServiceImpl;

import enterprise.elroi.data.models.Chats;
import enterprise.elroi.data.repositories.ChatsRepository;
import enterprise.elroi.dto.requests.ChatsRequests;
import enterprise.elroi.dto.responses.ChatsResponse;
import enterprise.elroi.exceptions.chatServiceExceptions.DeleteChatNoChatFoundException;
import enterprise.elroi.exceptions.chatServiceExceptions.GetChatByIdChatNotFoundException;
import enterprise.elroi.exceptions.chatServiceExceptions.NotAllowedToDeleteChatException;
import enterprise.elroi.services.chatServices.ChatServicesInterface;
import enterprise.elroi.util.mapper.chatMapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatServicesInterface {

    private final ChatsRepository repository;
    private final ChatMapper mapper;

    @Autowired
    public ChatServiceImpl(ChatsRepository repository, ChatMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatsResponse createChat(ChatsRequests request, String userId) {
        Chats chat = new Chats();
        chat.setParticipantIds(request.getParticipantIds());
        chat.setLastMessage(null);
        chat.setUpdatedAt(LocalDateTime.now());

        Chats savedChat = repository.save(chat);
        return mapper.toResponse(savedChat);
    }
    @Override
    public List<ChatsResponse> getChatsByUser(String userId) {
        List<Chats> chatsList = repository.findAll(); // optional: filter by participantIds
        List<ChatsResponse> responses = new ArrayList<>();

        for (Chats chat : chatsList) {
            if (chat.getParticipantIds() != null && chat.getParticipantIds().contains(userId)) {
                responses.add(mapper.toResponse(chat));
            }
        }

        return responses;
    }

    @Override
    public ChatsResponse getChatById(String chatId) {
        Chats chat = repository.findById(chatId)
                .orElseThrow(() -> new GetChatByIdChatNotFoundException("Chat not found"));

        return mapper.toResponse(chat);
    }
    @Override
    public void deleteChat(String chatId, String userId) {
        Chats chat = repository.findById(chatId)
                .orElseThrow(() -> new DeleteChatNoChatFoundException("Chat not found"));

        if (chat.getParticipantIds() == null || !chat.getParticipantIds().contains(userId)) {
            throw new NotAllowedToDeleteChatException("You are not allowed to delete this chat");
        }

        repository.delete(chat);
    }
}