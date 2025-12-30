package enterprise.elroi.data.repositories;

import enterprise.elroi.data.models.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository  extends MongoRepository<User, String> {
    Optional<User> findByEmail(@NotBlank String email);

    boolean existsByEmail(String email);
}
