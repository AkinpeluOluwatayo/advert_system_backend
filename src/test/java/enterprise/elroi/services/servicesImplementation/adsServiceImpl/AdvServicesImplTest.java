//package enterprise.elroi.services.servicesImplementation.adsServiceImpl;
//
//import enterprise.elroi.data.models.Ads;
//import enterprise.elroi.data.repositories.AdsRepository;
//import enterprise.elroi.dto.requests.AdsRequests;
//import enterprise.elroi.dto.responses.AdsResponse;
//import enterprise.elroi.exceptions.adsServiceExceptions.DeleteByIdAdvertNotFound;
//import enterprise.elroi.exceptions.adsServiceExceptions.GetByIdAdvertNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@SpringBootTest
//@ActiveProfiles("test") // Make sure this uses your test profile if needed
//public class AdvServicesImplTest {
//
//    @Autowired
//    private AdsRepository repository;
//
//    @Autowired
//    private AdvServicesImpl service;
//
//    @BeforeEach
//    public void setUp() {
//        repository.deleteAll();
//    }
//
//    @Test
//    public void testCreateAds() {
//        AdsRequests request = new AdsRequests();
//        request.setTitle("iPhone");
//        request.setDescription("New phone");
//        request.setPrice(500.0);
//        request.setLocation("Lagos");
//        request.setStatus("ACTIVE");
//
//        AdsResponse response = service.createAds(request, "User1");
//
//        assertEquals("iPhone", response.getTitle());
//        assertEquals("Lagos", response.getLocation());
//        assertNotNull(response.getCreatedAt());
//        assertFalse(repository.findAll().isEmpty());
//    }
//
//    @Test
//    public void testGetAdById() {
//        Ads ads = new Ads();
//        ads.setTitle("Samsung");
//        ads.setDescription("Android phone");
//        ads.setPrice(320.0);
//        ads.setLocation("Abuja");
//        ads.setUserId("User1");
//        ads.setCreatedAt(LocalDateTime.now());
//        ads.setUpdatedAt(LocalDateTime.now());
//
//        Ads savedAds = repository.save(ads);
//
//        AdsResponse response = service.getAdById(savedAds.getId());
//
//        assertEquals("Samsung", response.getTitle());
//        assertEquals("Abuja", response.getLocation());
//    }
//
//    @Test
//    public void testGetAdById_whenNotFound_shouldThrowException() {
//        assertThrows(
//                GetByIdAdvertNotFoundException.class,
//                () -> service.getAdById("invalid-id")
//        );
//    }
//
//    @Test
//    public void testGetAdsByUser() {
//        Ads ads = new Ads();
//        ads.setTitle("Laptop");
//        ads.setUserId("User2");
//        ads.setCreatedAt(LocalDateTime.now());
//        ads.setUpdatedAt(LocalDateTime.now());
//
//        repository.save(ads);
//
//        List<AdsResponse> responses = service.getAdsByUser("User2");
//
//        assertEquals(1, responses.size());
//        assertEquals("Laptop", responses.get(0).getTitle());
//    }
//
//    @Test
//    public void testDeleteAd() {
//        Ads ads = new Ads();
//        ads.setTitle("TV");
//        ads.setUserId("user3");
//        ads.setCreatedAt(LocalDateTime.now());
//        ads.setUpdatedAt(LocalDateTime.now());
//
//        Ads savedAds = repository.save(ads);
//
//        AdsResponse response = service.deleteAd(savedAds.getId(), "user3");
//
//        assertEquals("TV", response.getTitle());
//        assertTrue(repository.findById(savedAds.getId()).isEmpty());
//    }
//
//    @Test
//    public void testDeleteAd_whenNotFound_shouldThrowException() {
//        assertThrows(
//                DeleteByIdAdvertNotFound.class,
//                () -> service.deleteAd("wrong-id", "user1")
//        );
//    }
//
//    @Test
//    public void testGetAllAds_withFilters() {
//        Ads ads = new Ads();
//        ads.setTitle("Headset");
//        ads.setLocation("Lagos");
//        ads.setPrice(1200.0);
//        ads.setCreatedAt(LocalDateTime.now());
//        ads.setUpdatedAt(LocalDateTime.now());
//
//        repository.save(ads);
//
//        List<AdsResponse> responses =
//                service.getAllAds("macbook", "lagos", 1000.0, 1500.0);
//
//        assertEquals(1, responses.size());
//    }
//
//    @Test
//    public void testGetAllAds_whenNoMatch_shouldReturnEmpty() {
//        List<AdsResponse> responses =
//                service.getAllAds("Doesnt exist", null, null, null);
//
//        assertTrue(responses.isEmpty());
//    }
//}
