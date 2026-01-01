package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.AdsRequests;
import enterprise.elroi.dto.responses.AdsResponse;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.security.UserPrincipal;
import enterprise.elroi.services.adsServices.AdvServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/ads")
public class AdvController {

    private final AdvServicesInterface advServices;

    @Autowired
    public AdvController(AdvServicesInterface advServices) {
        this.advServices = advServices;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Map<String, Object>>> createAd(@RequestBody AdsRequests request, @AuthenticationPrincipal UserPrincipal user
    ) {if (user == null) {Map<String, Object> errorData = new HashMap<>();errorData.put("message", "Unauthorized: Missing or invalid token");
            return ResponseEntity.status(401).body(new ApiResponse<>(false, errorData));
        }AdsResponse response = advServices.createAds(request, user.getId());Map<String, Object> data = new HashMap<>();data.put("ad", response);
        return ResponseEntity.ok(new ApiResponse<>(true, data));
    }

    @GetMapping("/{adId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAdById(@PathVariable("adId") String adId) {
        try {AdsResponse response = advServices.getAdById(adId);if (response == null) {Map<String, Object> errorData = new HashMap<>();errorData.put("message", "Advertisement not found");
                return ResponseEntity.status(404).body(new ApiResponse<>(false, errorData));
            }Map<String, Object> data = new HashMap<>();data.put("ad", response);
            return ResponseEntity.ok(new ApiResponse<>(true, data));
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("message", "Invalid ID format provided");
            return ResponseEntity.status(400).body(new ApiResponse<>(false, errorData));
        } catch (Exception e) {Map<String, Object> errorData = new HashMap<>();errorData.put("message", "An internal error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(new ApiResponse<>(false, errorData));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAdsByUser(@AuthenticationPrincipal UserPrincipal user) {
        if (user == null) {Map<String, Object> errorData = new HashMap<>();errorData.put("message", "Unauthorized: Missing or invalid token");
            return ResponseEntity.status(401).body(new ApiResponse<>(false, errorData));
        }List<AdsResponse> responses = advServices.getAdsByUser(user.getId());Map<String, Object> data = new HashMap<>();data.put("ads", responses);
        return ResponseEntity.ok(new ApiResponse<>(true, data));
    }

    @DeleteMapping("/delete/{adId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deleteAd(@PathVariable("adId") String adId, @AuthenticationPrincipal UserPrincipal user
    ) {if (user == null) {Map<String, Object> errorData = new HashMap<>();errorData.put("message", "Unauthorized: Missing or invalid token");
            return ResponseEntity.status(401).body(new ApiResponse<>(false, errorData));
        }AdsResponse response = advServices.deleteAd(adId, user.getId());Map<String, Object> data = new HashMap<>();data.put("ad", response);
        return ResponseEntity.ok(new ApiResponse<>(true, data));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllAds(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "location", required = false) String location, @RequestParam(value = "minPrice", required = false) Double minPrice, @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        List<AdsResponse> responses = advServices.getAllAds(keyword, location, minPrice, maxPrice);Map<String, Object> data = new HashMap<>();data.put("ads", responses);
        return ResponseEntity.ok(new ApiResponse<>(true, data));
    }
}