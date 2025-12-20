package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.Ads;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AdsRepository extends MongoRepository<Ads, String> {
    List<Ads> findByUserId(String userId);
}
