package enterprise.elroi.data.models;
import org.bson.types.ObjectId;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Document(collection = "ads")
public class Ads {
    @Id
    private String id;
    private String title;
    private String description;
    private String location;
    private Double price;
    private String categoryId;
    private String status;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ArrayList<String> images;
}
