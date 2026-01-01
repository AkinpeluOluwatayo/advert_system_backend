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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (adId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be null");
        String cleanId = adId.trim().replaceAll("[^0-9a-fA-F]", "");
        Ads ads = repository.findById(cleanId)
                .orElseThrow(() -> new GetByIdAdvertNotFoundException("Advert not found with ID: " + cleanId));
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
        String cleanId = adId.trim().replaceAll("[^0-9a-fA-F]", "");
        Ads ads = repository.findById(cleanId)
                .orElseThrow(() -> new DeleteByIdAdvertNotFound("Advert not found"));
        if (!ads.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this advert");
        }
        repository.delete(ads);
        return mapper.toResponse(ads);
    }

    @Override
    public List<AdsResponse> getAllAds(String keyword, String location, Double minPrice, Double maxPrice) {
        List<Ads> adsList = repository.findAll();
        List<AdsResponse> responses = new ArrayList<>();
        for (Ads ads : adsList) {
            if (matchesFiltersSafe(ads, keyword, location, minPrice, maxPrice)) {
                responses.add(mapper.toResponse(ads));
            }
        }
        return responses;
    }

    private boolean matchesFiltersSafe(Ads ads, String keyword, String location, Double minPrice, Double maxPrice) {
        String adTitle = ads.getTitle() != null ? ads.getTitle() : "";
        String adLocation = ads.getLocation() != null ? ads.getLocation() : "";

        if (keyword != null && !adTitle.toLowerCase().contains(keyword.toLowerCase())) return false;
        if (location != null && !adLocation.equalsIgnoreCase(location)) return false;
        if (minPrice != null && (ads.getPrice() == null || ads.getPrice() < minPrice)) return false;
        if (maxPrice != null && (ads.getPrice() == null || ads.getPrice() > maxPrice)) return false;

        return true;
    }
}