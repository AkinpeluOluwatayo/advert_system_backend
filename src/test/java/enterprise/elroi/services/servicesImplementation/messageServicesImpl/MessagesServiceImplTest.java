//package enterprise.elroi.services.servicesImplementation.messageServicesImpl;
//
//
//import enterprise.elroi.data.models.Messages;
//import enterprise.elroi.data.repositories.MessagesRepository;
//import enterprise.elroi.dto.requests.MessagesRequests;
//import enterprise.elroi.dto.responses.MessagesResponse;
//import enterprise.elroi.services.messageServices.MessagesServicesInterface;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//public class MessagesServiceImplTest {
//    @Autowired
//    private MessagesRepository repository;
//
//    @Autowired
//    private MessagesServicesInterface service;
//
//    @BeforeEach
//    public void setup() {
//        repository.deleteAll();
//    }
//
//
//    @Test
//    public void testSendMessage() {
//        MessagesRequests request = new MessagesRequests();
//        request.setChatId("chat001");
//        request.setContent("Hello, how are you?");
//
//        MessagesResponse response = service.sendMessage(request, "user1");
//
//        assertNotNull(response);
//        assertEquals("chat001", response.getChatId());
//        assertEquals("user1", response.getSenderId());
//        assertEquals("Hello, how are you?", response.getContent());
//        assertNotNull(response.getCreatedAt());
//
//        assertEquals(1, repository.findAll().size());
//    }
//
//    @Test
//    public void testGetMessagesByChat() {
//        Messages message1 = new Messages();
//        message1.setChatId("chat001");
//        message1.setSenderId("user1");
//        message1.setContent("Hello");
//
//        Messages message2 = new Messages();
//        message2.setChatId("chat001");
//        message2.setSenderId("user2");
//        message2.setContent("Hi");
//
//        Messages message3 = new Messages();
//        message3.setChatId("chat002");
//        message3.setSenderId("user3");
//        message3.setContent("Different chat");
//
//        repository.save(message1);
//        repository.save(message2);
//        repository.save(message3);
//        ArrayList<MessagesResponse> responses =
//                new ArrayList<>(service.getMessagesByChat("chat001"));
//
//        assertNotNull(responses);
//        assertEquals(2, responses.size());
//
//        for (MessagesResponse response : responses) {
//            assertEquals("chat001", response.getChatId());
//        }
//    }
//}