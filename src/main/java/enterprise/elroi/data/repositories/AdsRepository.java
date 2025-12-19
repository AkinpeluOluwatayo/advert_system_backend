package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.Ads;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdsRepository extends MongoRepository<Ads, String> {
}
