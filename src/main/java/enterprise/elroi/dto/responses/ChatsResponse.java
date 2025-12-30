package enterprise.elroi.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Data
public class ChatsResponse {

    private  String adId;
//   private  ArrayList<String> participantIds;
    private String participantId;
    private  String lastMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
   private  LocalDateTime updatedAt;
}
