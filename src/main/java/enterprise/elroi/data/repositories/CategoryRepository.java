package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository  extends MongoRepository<Category, String> {
}
