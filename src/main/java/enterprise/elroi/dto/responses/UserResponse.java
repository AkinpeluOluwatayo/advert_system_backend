package enterprise.elroi.dto.responses;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String email;
    private String phoneNumber;
    private String address;
    private String roles;
}
