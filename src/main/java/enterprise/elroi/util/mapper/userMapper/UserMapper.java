package enterprise.elroi.util.mapper.userMapper;

import enterprise.elroi.data.models.User;
import enterprise.elroi.dto.responses.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        if (user == null) return null;

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAddress(user.getAddress());
        response.setRoles(user.getRoles());

        return response;
    }
}