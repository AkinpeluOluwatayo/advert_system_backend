package enterprise.elroi.services.userServices;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponse;

import java.util.List;

public interface UserServicesInterface {

    UserResponse getUserById(String userId);


    List<UserResponse> getAllUsers();


    UserResponse updateUser(String userId, UserRequests request);

    void deleteUser(String userId);


}
