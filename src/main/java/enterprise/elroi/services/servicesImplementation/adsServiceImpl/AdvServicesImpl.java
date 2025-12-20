package enterprise.elroi.services.servicesImplementation.adsServiceImpl;

import enterprise.elroi.data.models.Ads;
import enterprise.elroi.data.repositories.AdsRepository;
import enterprise.elroi.dto.requests.AdsRequests;
import enterprise.elroi.dto.responses.AdsResponse;
import enterprise.elroi.exceptions.adsServiceExceptions.DeleteByIdAdvertNotFound;
import enterprise.elroi.exceptions.adsServiceExceptions.GetByIdAdvertNotFoundException;
import enterprise.elroi.services.adsServices.AdvServicesInterface;
import enterprise.elroi.util.mapper.advMapper.AdvMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvServicesImpl implements AdvServicesInterface {

    private final AdsRepository repository;
    private final AdvMapper mapper;

    @Autowired
    public AdvServicesImpl(AdsRepository repository, AdvMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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

        Ads savedAds = repository.save(ads);

        return mapper.toResponse(savedAds);
    }

    @Override
    public AdsResponse getAdById(String adId) {
        Ads ads = repository.findById(adId)
                .orElseThrow(() -> new GetByIdAdvertNotFoundException("Advert not found"));

        return mapper.toResponse(ads);
    }

    @Override
    public List<AdsResponse> getAdsByUser(String userId) {
        List<Ads> adsList = repository.findByUserId(userId);
        List<AdsResponse> responses = new ArrayList<>();

        for (Ads ads : adsList) {
            responses.add(mapper.toResponse(ads));
        }

        return responses;
    }

    @Override
    public AdsResponse deleteAd(String adId, String userId) {
        Ads ads = repository.findById(adId)
                .orElseThrow(() -> new DeleteByIdAdvertNotFound("Advert not found"));

        if (!ads.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this advert");
        }

        repository.delete(ads);

        return mapper.toResponse(ads);
    }

    @Override
    public List<AdsResponse> getAllAds(String keyword, String location, Double minPrice, Double maxPrice) {
        List<Ads> adsList = repository.findAll();
        List<AdsResponse> responses = new ArrayList<>();

        for (Ads ads : adsList) {
            if (!matchesFilters(ads, keyword, location, minPrice, maxPrice)) {
                continue;
            }
            responses.add(mapper.toResponse(ads));
        }

        return responses;
    }

    private boolean matchesFilters(Ads ads, String keyword, String location, Double minPrice, Double maxPrice) {
        if (keyword != null && !ads.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
            return false;
        }
        if (location != null && !ads.getLocation().equalsIgnoreCase(location)) {
            return false;
        }
        if (minPrice != null && ads.getPrice() < minPrice) {
            return false;
        }
        return maxPrice == null || ads.getPrice() <= maxPrice;
    }
}
