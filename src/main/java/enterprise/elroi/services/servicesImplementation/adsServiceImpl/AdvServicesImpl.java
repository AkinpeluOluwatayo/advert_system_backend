package enterprise.elroi.services.servicesImplementation.adsServiceImpl;

import enterprise.elroi.data.models.Ads;
import enterprise.elroi.data.repositories.AdsRepository;
import enterprise.elroi.dto.requests.AdsRequests;
import enterprise.elroi.dto.responses.AdsResponse;
import enterprise.elroi.services.adsServices.AdvServicesInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class AdvServicesImpl implements AdvServicesInterface {
    private AdsRepository repository;

    @Autowired
    public AdvServicesImpl(AdsRepository repository) {this.repository = repository;}

    @Override
    public AdsResponse createAds(AdsRequests requests, String userId) {

        Ads ads = new Ads();
        ads.setTitle(requests.getTitle());
        ads.setDescription(requests.getDescription());
        ads.setPrice(requests.getPrice());
        ads.setLocation(requests.getLocation());
        ads.setStatus(requests.getStatus());
        ads.setImages(requests.getImages());
        ads.setUserId(userId);
        ads.setCreatedAt(LocalDateTime.now());
        ads.setUpdatedAt(LocalDateTime.now());

            repository.save(ads);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");

        AdsResponse response  = new AdsResponse();

        response.setTitle(requests.getTitle());
        response.setDescription(requests.getDescription());
        response.setPrice(requests.getPrice());
        response.setLocation(requests.getLocation());
        response.setStatus(requests.getStatus());
        response.setImages(requests.getImages());
        response.setCreatedAt(LocalDateTime.parse(ads.getCreatedAt().format(formatter)));
        response.setUpdatedAt(LocalDateTime.parse(ads.getUpdatedAt().format(formatter)));



        return response;
    }

    @Override
    public AdsResponse getAdById(String adId) {
        return null;
    }

    @Override
    public List<AdsResponse> getAdsByUser(String userId) {
        return List.of();
    }

    @Override
    public AdsResponse deleteAd(String adId, String userId) {
        return null;
    }

    @Override
    public List<AdsResponse> getAllAds(String keyword, String location, Double minPrice, Double maxPrice) {
        return List.of();
    }
}
