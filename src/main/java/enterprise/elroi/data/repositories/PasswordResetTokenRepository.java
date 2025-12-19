package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, String> {
}
