package enterprise.elroi.services.adsServices;

import enterprise.elroi.dto.requests.AdsRequests;
import enterprise.elroi.dto.responses.AdsResponse;

import java.util.List;

public interface AdvServicesInterface {
    AdsResponse createAds (AdsRequests requests, String userId);
    AdsResponse getAdById(String adId);
    List<AdsResponse> getAdsByUser(String userId);
    AdsResponse deleteAd(String adId, String userId);
    List<AdsResponse> getAllAds(String keyword, String location, Double minPrice, Double maxPrice);
}
