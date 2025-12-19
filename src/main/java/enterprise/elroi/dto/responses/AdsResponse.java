package enterprise.elroi.dto.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class AdsResponse {

    private String title;
    private String description;
    private Double price;
    private String location;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ArrayList<String> images;
}
