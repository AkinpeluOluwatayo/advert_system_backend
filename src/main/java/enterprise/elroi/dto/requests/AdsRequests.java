package enterprise.elroi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AdsRequests {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Double price;
    @NotBlank
    private String location;
    @NotBlank
    private ArrayList<String> images;
    @NotBlank
    private String status;
}
