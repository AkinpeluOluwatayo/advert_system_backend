package enterprise.elroi.services.authServices;

import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponse;

public interface AuthServicesInterface {
    UserResponse register(UserRequests request);

    UserResponse login(String email, String password);

    UserResponse adminLogin(String email, String password);


    UserResponse getCurrentUser(String userId);


    void forgotPassword(String email);


    void resetPassword(String token, String newPassword);
}
