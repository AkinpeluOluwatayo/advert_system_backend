package enterprise.elroi.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "passwordReset")
public class PasswordResetToken {
    private String id;
    private String userId;
    private String token;
    private String used;
}
