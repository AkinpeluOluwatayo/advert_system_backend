//package enterprise.elroi.services.servicesImplementation.chatServiceImpl;
//
//import enterprise.elroi.data.models.Chats;
//import enterprise.elroi.data.repositories.ChatsRepository;
//import enterprise.elroi.dto.requests.ChatsRequests;
//import enterprise.elroi.dto.responses.ChatsResponse;
//import enterprise.elroi.exceptions.chatServiceExceptions.NotAllowedToDeleteChatException;
//import enterprise.elroi.services.chatServices.ChatServicesInterface;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ChatServiceImplTest {
//    @Autowired
//    private ChatsRepository repository;
//
//    @Autowired
//    private ChatServicesInterface service;
//
//    @BeforeEach
//    public void setup() {
//        repository.deleteAll();
//    }
//    @Test
//    public void testCreateChat() {
//        ChatsRequests request = new ChatsRequests();
//
//        ArrayList<String> participants = new ArrayList<>();
//        participants.add("user1");
//        participants.add("user2");
//
//        request.setParticipantIds(participants);
//
//        ChatsResponse response = service.createChat(request, "user1");
//
//        assertNotNull(response);
//        assertEquals(2, response.getParticipantIds().size());
//        assertTrue(response.getParticipantIds().contains("user1"));
//        assertTrue(response.getParticipantIds().contains("user2"));
//        assertNull(response.getLastMessage());
//        assertNotNull(response.getUpdatedAt());
//
//        assertEquals(1, repository.findAll().size());
//    }
//    @Test
//    public void testGetChatsByUser() {
//        Chats chat1 = new Chats();
//        ArrayList<String> participants1 = new ArrayList<>();
//        participants1.add("user1");
//        participants1.add("user2");
//        chat1.setParticipantIds(participants1);
//
//        Chats chat2 = new Chats();
//        ArrayList<String> participants2 = new ArrayList<>();
//        participants2.add("user3");
//        participants2.add("user1");
//        chat2.setParticipantIds(participants2);
//
//        repository.save(chat1);
//        repository.save(chat2);
//        ArrayList<ChatsResponse> responses =
//                new ArrayList<>(service.getChatsByUser("user1"));
//
//        assertNotNull(responses);
//        assertEquals(2, responses.size());
//    }
//    @Test
//    public void testGetChatById() {
//        Chats chat = new Chats();
//
//        ArrayList<String> participants = new ArrayList<>();
//        participants.add("user1");
//        participants.add("user2");
//
//        chat.setParticipantIds(participants);
//
//        Chats savedChat = repository.save(chat);
//
//        ChatsResponse response = service.getChatById(savedChat.getId());
//
//        assertNotNull(response);
//        assertEquals(2, response.getParticipantIds().size());
//        assertTrue(response.getParticipantIds().contains("user1"));
//    }
//    @Test
//    public void testDeleteChatSuccessfully() {
//        Chats chat = new Chats();
//
//        ArrayList<String> participants = new ArrayList<>();
//        participants.add("user1");
//        participants.add("user2");
//
//        chat.setParticipantIds(participants);
//
//        Chats savedChat = repository.save(chat);
//
//        service.deleteChat(savedChat.getId(), "user1");
//
//        assertTrue(repository.findAll().isEmpty());
//    }
//    @Test
//    public void testDeleteChatNotAllowed() {
//        Chats chat = new Chats();
//
//        ArrayList<String> participants = new ArrayList<>();
//        participants.add("user1");
//        participants.add("user2");
//
//        chat.setParticipantIds(participants);
//
//        Chats savedChat = repository.save(chat);
//
//        assertThrows(
//                NotAllowedToDeleteChatException.class,
//                () -> service.deleteChat(savedChat.getId(), "user3")
//        );
//    }
//}