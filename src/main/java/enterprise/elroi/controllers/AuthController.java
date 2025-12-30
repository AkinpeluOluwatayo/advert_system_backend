package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.dto.responses.UserResponse;
import enterprise.elroi.exceptions.authServiceExceptions.UserAlreadyExistException;
import enterprise.elroi.security.JwtUtils;
import enterprise.elroi.security.UserPrincipal;
import enterprise.elroi.services.authServices.AuthServicesInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    private final AuthServicesInterface authService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthServicesInterface authService, JwtUtils jwtUtils) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, Object>>> register(
            @RequestBody UserRequests request
    ) {
        try {
            UserResponse registeredUser = authService.register(request);
            String token = generateToken(registeredUser);

            Map<String, Object> data = new HashMap<>();
            data.put("user", registeredUser);
            data.put("token", token);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, data));

        } catch (UserAlreadyExistException e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, errorData));
        } catch (Exception e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("message", "Registration failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, errorData));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(
            @RequestBody UserRequests request
    ) {
        try {
            UserResponse userResponse =
                    authService.login(request.getEmail(), request.getPassword());

            String token = generateToken(userResponse);

            Map<String, Object> data = new HashMap<>();
            data.put("user", userResponse);
            data.put("token", token);

            return ResponseEntity.ok(new ApiResponse<>(true, data));

        } catch (Exception e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, errorData));
        }
    }


    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> adminLogin(
            @RequestBody UserRequests request
    ) {
        try {
            UserResponse adminResponse = authService.adminLogin(request.getEmail(), request.getPassword());
            String token = generateToken(adminResponse);

            Map<String, Object> data = new HashMap<>();
            data.put("admin", adminResponse);
            data.put("token", token);

            return ResponseEntity.ok(new ApiResponse<>(true, data));

        } catch (Exception e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("message", "Invalid admin credentials");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, errorData));
        }
    }

    private String generateToken(UserResponse userResponse) {
        UserPrincipal principal = new UserPrincipal(
                userResponse.getId(),
                userResponse.getEmail(),
                userResponse.getRoles()
        );
        return jwtUtils.generateJwtToken(principal);
    }
}
