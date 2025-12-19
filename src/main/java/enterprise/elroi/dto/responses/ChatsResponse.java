package enterprise.elroi.dto.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Data
public class ChatsResponse {

   private  String adId;
   private  ArrayList<String> participantIds;
   private  String lastMessage;
   private  LocalDateTime updatedAt;
}
