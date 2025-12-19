package enterprise.elroi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessagesRequests {
    @NotBlank
    private String chatId;
    @NotBlank
   private String content;
}
