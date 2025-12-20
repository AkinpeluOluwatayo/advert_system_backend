package enterprise.elroi.services.servicesImplementation.authServicesImpl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import enterprise.elroi.data.models.PasswordResetToken;
import enterprise.elroi.data.models.User;
import enterprise.elroi.data.repositories.PasswordResetTokenRepository;
import enterprise.elroi.data.repositories.UserRepository;
import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponse;
import enterprise.elroi.exceptions.authServiceExceptions.*;
import enterprise.elroi.services.authServices.AuthServicesInterface;
import enterprise.elroi.util.mapper.authMapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthServicesInterface {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final AuthMapper mapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordResetTokenRepository tokenRepository,
                           AuthMapper mapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse register(UserRequests request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User already exists with this email");
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, request.getPassword().toCharArray());
        User user = mapper.toUser(request);
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);
        return mapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserLoginNotFoundException("User not found"));

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!result.verified) {
            throw new InvalidPasswordException("Invalid password");
        }

        return mapper.toUserResponse(user);
    }

    @Override
    public UserResponse adminLogin(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));

        if (!"ADMIN".equalsIgnoreCase(user.getRoles())) {
            throw new UserIsNotAnAdminException("User is not an admin");
        }

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!result.verified) {
            throw new InvalidAdminPassword("Invalid Admin password");
        }

        return mapper.toUserResponse(user);
    }

    @Override
    public UserResponse getCurrentUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserCurrentLoginNotFoundException("User not found"));

        return mapper.toUserResponse(user);
    }

    @Override
    public void logout(String userId) {
        System.out.println("User logged out: " + userId);
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserForgotPasswordEmailNotFoundException("User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUserId(user.getId());
        resetToken.setToken(token);
        resetToken.setUsed("false"); 

        tokenRepository.save(resetToken);

        // Send email with reset link (replace with real email sending)
        System.out.println("Reset link: http://yourapp.com/reset-password?token=" + token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token"));

        if ("true".equalsIgnoreCase(resetToken.getUsed())) {
            throw new TokenHasBeenUsedException("Token has already been used");
        }

        User user = userRepository.findById(resetToken.getUserId())
                .orElseThrow(() -> new UserNotFoundTokenException("User not found"));

        String hashedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        
        resetToken.setUsed("true");
        tokenRepository.save(resetToken);

        System.out.println("Password successfully reset for user: " + user.getEmail());
    }
}
