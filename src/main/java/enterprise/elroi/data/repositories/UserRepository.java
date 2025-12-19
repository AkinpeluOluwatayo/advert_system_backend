package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, String> {
}
