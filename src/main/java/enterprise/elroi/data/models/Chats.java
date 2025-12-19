package enterprise.elroi.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Document(collection = "chats")
public class Chats {
    @Id
    private String id;
    private ArrayList<String> participantIds;
    private String lastMessage;
    private LocalDateTime updatedAt;
}
