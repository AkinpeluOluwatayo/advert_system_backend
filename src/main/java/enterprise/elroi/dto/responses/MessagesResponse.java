package enterprise.elroi.dto.responses;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MessagesResponse {

    private String chatId;

    private String senderId;

    private String content;

    private LocalDateTime createdAt;
}
