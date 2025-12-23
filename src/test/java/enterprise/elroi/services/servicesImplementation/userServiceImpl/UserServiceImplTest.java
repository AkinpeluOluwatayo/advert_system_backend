//package enterprise.elroi.services.servicesImplementation.userServiceImpl;
//import enterprise.elroi.data.models.User;
//import enterprise.elroi.data.repositories.UserRepository;
//import enterprise.elroi.dto.requests.UserRequests;
//import enterprise.elroi.dto.responses.UserResponse;
//import enterprise.elroi.exceptions.userServiceException.DeleteUserByIdNotFoundException;
//import enterprise.elroi.exceptions.userServiceException.GetUserByIdNotFoundException;
//import enterprise.elroi.exceptions.userServiceException.UpdateUserByIdNotFoundException;
//import enterprise.elroi.services.userServices.UserServicesInterface;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class UserServiceImplTest {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private UserServicesInterface service;
//
//    @BeforeEach
//    public void setup() {
//        repository.deleteAll();
//    }
//    @Test
//    public void testGetUserById() {
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPhoneNumber("08012345678");
//        user.setAddress("Lagos");
//        user.setRoles("USER");
//
//        User savedUser = repository.save(user);
//
//        UserResponse response = service.getUserById(savedUser.getId());
//
//        assertNotNull(response);
//        assertEquals(savedUser.getId(), response.getId());
//        assertEquals("test@example.com", response.getEmail());
//        assertEquals("08012345678", response.getPhoneNumber());
//        assertEquals("Lagos", response.getAddress());
//        assertEquals("USER", response.getRoles());
//    }
//
//    @Test
//    public void testGetUserByIdNotFound() {
//        assertThrows(
//                GetUserByIdNotFoundException.class,
//                () -> service.getUserById("invalid-id")
//        );
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        User user1 = new User();
//        user1.setEmail("user1@mail.com");
//        user1.setRoles("USER");
//
//        User user2 = new User();
//        user2.setEmail("user2@mail.com");
//        user2.setRoles("ADMIN");
//        repository.save(user1);
//        repository.save(user2);
//
//        assertEquals(2, service.getAllUsers().size());
//    }
//
//    @Test
//    public void testUpdateUser() {
//        User user = new User();
//        user.setEmail("old@mail.com");
//        user.setPhoneNumber("08000000000");
//        user.setAddress("Old Address");
//        user.setRoles("USER");
//
//        User savedUser = repository.save(user);
//        UserRequests request = new UserRequests();
//        request.setEmail("new@mail.com");
//        request.setPhoneNumber("08111111111");
//        request.setAddress("New Address");
//        request.setRoles("ADMIN");
//
//        UserResponse response = service.updateUser(savedUser.getId(), request);
//
//        assertNotNull(response);
//        assertEquals("new@mail.com", response.getEmail());
//        assertEquals("08111111111", response.getPhoneNumber());
//        assertEquals("New Address", response.getAddress());
//        assertEquals("ADMIN", response.getRoles());
//    }
//
//    @Test
//    public void testUpdateUserNotFound() {
//        UserRequests request = new UserRequests();
//        request.setEmail("new@mail.com");
//        request.setRoles("ADMIN");
//
//        assertThrows(
//                UpdateUserByIdNotFoundException.class,
//                () -> service.updateUser("invalid-id", request)
//        );
//    }
//
//
//    @Test
//    public void testDeleteUser() {
//        User user = new User();
//        user.setEmail("delete@mail.com");
//        user.setRoles("USER");
//
//        User savedUser = repository.save(user);
//
//        service.deleteUser(savedUser.getId());
//
//        assertTrue(repository.findAll().isEmpty());
//    }
//    @Test
//    public void testDeleteUserNotFound() {
//        assertThrows(
//                DeleteUserByIdNotFoundException.class,
//                () -> service.deleteUser("invalid-id")
//        );
//    }
//
//}