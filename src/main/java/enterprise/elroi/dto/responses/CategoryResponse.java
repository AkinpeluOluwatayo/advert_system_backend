package enterprise.elroi.dto.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponse {
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
