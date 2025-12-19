package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessagesRepository extends MongoRepository<Messages, String> {
}
