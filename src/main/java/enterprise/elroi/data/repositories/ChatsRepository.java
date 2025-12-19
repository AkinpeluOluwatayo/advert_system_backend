package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.Chats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatsRepository extends MongoRepository<Chats, String> {
}
