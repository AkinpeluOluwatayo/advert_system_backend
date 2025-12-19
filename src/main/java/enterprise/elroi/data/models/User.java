package enterprise.elroi.data.models;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String roles;
    private LocalDateTime createdAt;

}
