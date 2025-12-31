package enterprise.elroi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequests {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String roles;

}
