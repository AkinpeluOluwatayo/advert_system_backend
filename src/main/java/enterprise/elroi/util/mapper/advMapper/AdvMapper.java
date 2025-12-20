package enterprise.elroi.util.mapper.advMapper;

import enterprise.elroi.data.models.Ads;
import enterprise.elroi.dto.responses.AdsResponse;
import org.springframework.stereotype.Component;

@Component
public class AdvMapper {
    public AdsResponse toResponse(Ads ads) {
        AdsResponse response = new AdsResponse();
        response.setTitle(ads.getTitle());
        response.setDescription(ads.getDescription());
        response.setPrice(ads.getPrice());
        response.setLocation(ads.getLocation());
        response.setStatus(ads.getStatus());
        response.setImages(ads.getImages());
        response.setCreatedAt(ads.getCreatedAt());
        response.setUpdatedAt(ads.getUpdatedAt());
        return response;
    }
}
