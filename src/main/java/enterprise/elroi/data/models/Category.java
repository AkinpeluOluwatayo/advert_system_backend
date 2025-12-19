package enterprise.elroi.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document (collection = "categories")
public class Category {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
