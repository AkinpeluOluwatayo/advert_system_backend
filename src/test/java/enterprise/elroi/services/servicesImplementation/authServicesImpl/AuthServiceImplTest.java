//package enterprise.elroi.services.servicesImplementation.authServicesImpl;
//
//import enterprise.elroi.data.models.PasswordResetToken;
//import enterprise.elroi.data.models.User;
//import enterprise.elroi.data.repositories.PasswordResetTokenRepository;
//import enterprise.elroi.data.repositories.UserRepository;
//import enterprise.elroi.dto.requests.UserRequests;
//import enterprise.elroi.dto.responses.UserResponse;
//import enterprise.elroi.exceptions.authServiceExceptions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class AuthServiceImplTest {
//
//    @Autowired
//    private AuthServiceImpl authService;
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordResetTokenRepository tokenRepository;
//
//    @BeforeEach
//   public void setUp() {
//        tokenRepository.deleteAll();
//        userRepository.deleteAll();
//    }
//
//
//    @Test
//   public void testRegisterUser() {
//        UserRequests request = new UserRequests();
//        request.setEmail("test@mail.com");
//        request.setPassword("password123");
//        request.setAddress("Lagos");
//        request.setPhoneNumber("0800000000");
//        request.setRoles("USER");
//        UserResponse response = authService.register(request);
//
//        assertEquals("test@mail.com", response.getEmail());
//        assertEquals("USER", response.getRoles());
//        assertNotNull(response.getId());
//        assertEquals(1, userRepository.findAll().size());
//    }
//
//    @Test
//   public void testRegisterUser_whenEmailExists_shouldThrowException() {
//        User user = new User();
//        user.setEmail("test@mail.com");
//        user.setPassword("hashed");
//        user.setRoles("USER");
//
//        userRepository.save(user);
//
//        UserRequests request = new UserRequests();
//        request.setEmail("test@mail.com");
//        request.setPassword("password123");
//        assertThrows(
//                UserAlreadyExistException.class,
//                () -> authService.register(request)
//        );
//    }
//
//
//
//    @Test
//    public void testLoginUser() {
//        UserRequests request = new UserRequests();
//        request.setEmail("login@mail.com");
//        request.setPassword("mypassword");
//        request.setRoles("USER");
//
//        authService.register(request);
//
//        UserResponse response = authService.login("login@mail.com", "mypassword");
//
//        assertEquals("login@mail.com", response.getEmail());
//    }
//
//    @Test
//    public void testLoginUser_whenUserNotFound_shouldThrowException() {
//        assertThrows(
//                UserLoginNotFoundException.class,
//                () -> authService.login("wrong@mail.com", "password")
//        );
//    }
//
//    @Test
//   public void testLoginUser_whenPasswordWrong_shouldThrowException() {
//        UserRequests request = new UserRequests();
//        request.setEmail("wrongpass@mail.com");
//        request.setPassword("correct");
//        request.setRoles("USER");
//
//        authService.register(request);
//
//        assertThrows(
//                InvalidPasswordException.class,
//                () -> authService.login("wrongpass@mail.com", "incorrect")
//        );
//    }
//    @Test
//    public void testAdminLogin() {
//        UserRequests request = new UserRequests();
//        request.setEmail("admin@mail.com");
//        request.setPassword("adminpass");
//        request.setRoles("ADMIN");
//
//        authService.register(request);
//
//        UserResponse response = authService.adminLogin("admin@mail.com", "adminpass");
//
//        assertEquals("ADMIN", response.getRoles());
//    }
//
//    @Test
//    public void testAdminLogin_whenNotAdmin_shouldThrowException() {
//        UserRequests request = new UserRequests();
//        request.setEmail("user@mail.com");
//        request.setPassword("password");
//        request.setRoles("USER");
//
//        authService.register(request);
//        assertThrows(
//                UserIsNotAnAdminException.class,
//                () -> authService.adminLogin("user@mail.com", "password")
//        );
//    }
//
//
//    @Test
//   public void testGetCurrentUser() {
//        UserRequests request = new UserRequests();
//        request.setEmail("current@mail.com");
//        request.setPassword("password");
//        request.setRoles("USER");
//
//        UserResponse saved = authService.register(request);
//
//        UserResponse response = authService.getCurrentUser(saved.getId());
//
//        assertEquals("current@mail.com", response.getEmail());
//    }
//
//    @Test
//   public void testGetCurrentUser_whenNotFound_shouldThrowException() {
//        assertThrows(
//                UserCurrentLoginNotFoundException.class,
//                () -> authService.getCurrentUser("invalid-id")
//        );
//    }
//
//
//
//    @Test
//   public void testForgotPassword() {
//        UserRequests request = new UserRequests();
//        request.setEmail("forgot@mail.com");
//        request.setPassword("password");
//        request.setRoles("USER");
//
//        authService.register(request);
//
//        authService.forgotPassword("forgot@mail.com");
//
//        assertEquals(1, tokenRepository.findAll().size());
//    }
//    @Test
//    public void testForgotPassword_whenEmailNotFound_shouldThrowException() {
//        assertThrows(
//                UserForgotPasswordEmailNotFoundException.class,
//                () -> authService.forgotPassword("none@mail.com")
//        );
//    }
//
//
//
//    @Test
//    public void testResetPassword() {
//        UserRequests request = new UserRequests();
//        request.setEmail("reset@mail.com");
//        request.setPassword("old pass");
//        request.setRoles("USER");
//
//        UserResponse userResponse = authService.register(request);
//
//        PasswordResetToken token = new PasswordResetToken();
//        token.setUserId(userResponse.getId());
//        token.setToken("reset-token");
//        token.setUsed("false");
//
//        tokenRepository.save(token);
//        authService.resetPassword("reset-token", "newpass");
//
//        PasswordResetToken updatedToken =
//                tokenRepository.findByToken("reset-token").get();
//
//        assertEquals("true", updatedToken.getUsed());
//    }
//
//    @Test
//    public void testResetPassword_whenTokenInvalid_shouldThrowException() {
//        assertThrows(InvalidTokenException.class, () -> authService.resetPassword("bad-token", "newpass")
//        );
//    }
//}