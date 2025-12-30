package enterprise.elroi.util.mapper.advMapper;

import enterprise.elroi.data.models.Ads;
import enterprise.elroi.dto.responses.AdsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AdvMapper {
    public AdsResponse toResponse(Ads ads) {
        if (ads == null) return null;

        AdsResponse response = new AdsResponse();

        // CHANGE: Just use ads.getId() directly because it is now a String
        response.setId(ads.getId() != null ? ads.getId() : "");

        response.setTitle(ads.getTitle() != null ? ads.getTitle() : "");
        response.setDescription(ads.getDescription() != null ? ads.getDescription() : "");
        response.setPrice(ads.getPrice() != null ? ads.getPrice() : 0.0);
        response.setLocation(ads.getLocation() != null ? ads.getLocation() : "");
        response.setStatus(ads.getStatus() != null ? ads.getStatus() : "");
        response.setImages(ads.getImages() != null ? ads.getImages() : new ArrayList<>());
        response.setCreatedAt(ads.getCreatedAt());
        response.setUpdatedAt(ads.getUpdatedAt());

        return response;
    }
}