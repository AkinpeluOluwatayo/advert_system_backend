package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.Chats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatsRepository extends MongoRepository<Chats, String> {
    List<Chats> findByParticipantIdContaining(String userId);

}
