package enterprise.elroi.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class Messages {
    @Id
    private String id;

    private String chatId;

    private String senderId;

    private String content;

    private LocalDateTime createdAt;
}
