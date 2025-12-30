package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.dto.responses.UserResponse;
import enterprise.elroi.services.userServices.UserServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/users")
public class UserController {

    private final UserServicesInterface userService;

    @Autowired
    public UserController(UserServicesInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable String userId) {
        UserResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, response));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> responses = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(true, responses));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable String userId, @RequestBody UserRequests request) {
        UserResponse response = userService.updateUser(userId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, response));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully"));
    }
}
