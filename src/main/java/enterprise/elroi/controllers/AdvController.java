package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.AdsRequests;
import enterprise.elroi.dto.responses.AdsResponse;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.services.adsServices.AdvServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdvController {

    private final AdvServicesInterface advServices;

    @Autowired
    public AdvController(AdvServicesInterface advServices) {
        this.advServices = advServices;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAd(@RequestBody AdsRequests request, @PathVariable("userId") String userId) {
        try {
            AdsResponse response = advServices.createAds(request, userId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{adId}")
    public ResponseEntity<?> getAdById(@PathVariable("adId") String adId) {
        try {
            AdsResponse response = advServices.getAdById(adId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAdsByUser(@PathVariable("userId") String userId) {
        try {
            List<AdsResponse> responses = advServices.getAdsByUser(userId);
            return new ResponseEntity<>(new ApiResponse(true, responses), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{adId}/{userId}")
    public ResponseEntity<?> deleteAd(@PathVariable("adId") String adId, @PathVariable("userId") String userId) {
        try {
            AdsResponse response = advServices.deleteAd(adId, userId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAds(@RequestParam(required = false) String keyword, @RequestParam(required = false) String location, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice
    ) {
        try {
            List<AdsResponse> responses = advServices.getAllAds(keyword, location, minPrice, maxPrice);
            return new ResponseEntity<>(new ApiResponse(true, responses), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
