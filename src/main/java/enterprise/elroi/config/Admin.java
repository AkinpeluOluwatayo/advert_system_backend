package enterprise.elroi.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import enterprise.elroi.data.models.User;
import enterprise.elroi.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Admin implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public Admin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@dealBridge.com";
        String adminPassword = "admin1234";
        String adminRole = "ADMIN";

        Optional<User> existingAdmin = userRepository.findByEmail(adminEmail);
        if (existingAdmin.isEmpty()) {

            String hashedPassword = BCrypt.withDefaults().hashToString(12, adminPassword.toCharArray());

            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setPassword(hashedPassword);
            admin.setRoles(adminRole);
            admin.setAddress("SemiColon");
            admin.setPhoneNumber("09137452733");

            userRepository.save(admin);

            System.out.println("Default admin user created with email: " + adminEmail);
        } else {
            System.out.println("Admin user already exists: " + adminEmail);
        }
    }
}
