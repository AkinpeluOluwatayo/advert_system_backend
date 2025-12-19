package enterprise.elroi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequests {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
