package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.dto.responses.UserResponse;
import enterprise.elroi.exceptions.authServiceExceptions.UserForgotPasswordEmailNotFoundException;
import enterprise.elroi.services.authServices.AuthServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    private final AuthServicesInterface authService;

    @Autowired
    public AuthController(AuthServicesInterface authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequests request) {
        try {
            UserResponse response = authService.register(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequests request) {
        try {

            UserResponse response = authService.login(request.getEmail(), request.getPassword());
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody UserRequests request) {
        try {
            UserResponse response = authService.adminLogin(request.getEmail(), request.getPassword());
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/current/{userId}")
    public ResponseEntity<?> getCurrentUser(@PathVariable("userId") String userId) {
        try {
            UserResponse response = authService.getCurrentUser(userId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        try {
            authService.forgotPassword(email);
            return ResponseEntity.ok(new ApiResponse(true, "Password reset link sent to email"));
        } catch (UserForgotPasswordEmailNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam("token") String token,
            @RequestParam("newPassword") String newPassword) {
        try {
            authService.resetPassword(token, newPassword);
            return new ResponseEntity<>(new ApiResponse(true, "Password successfully reset"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
