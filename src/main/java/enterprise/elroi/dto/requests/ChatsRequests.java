package enterprise.elroi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.util.ArrayList;

@Data
public class ChatsRequests {
    @NotBlank
   private String adId;
    private ArrayList<String> participantIds;
}
