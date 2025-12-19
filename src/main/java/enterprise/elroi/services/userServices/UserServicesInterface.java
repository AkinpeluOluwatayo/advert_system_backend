package enterprise.elroi.services.userServices;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponse;

import java.util.List;

public interface UserServicesInterface {

    UserResponse getUserById(String userId);

    // Get all users (optional, admin use)
    List<UserResponse> getAllUsers();


    UserResponse updateUser(String userId, UserRequests request);

    // Delete a user (optional, admin use)
    void deleteUser(String userId);


}
