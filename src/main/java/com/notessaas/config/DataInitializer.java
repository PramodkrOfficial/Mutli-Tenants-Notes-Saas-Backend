package com.notessaas.config;


import com.notessaas.model.Tenant;
import com.notessaas.model.User;
import com.notessaas.repository.TenantRepository;
import com.notessaas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize tenants if they don't exist
        if (tenantRepository.count() == 0) {
            Tenant acme = tenantRepository.save(new Tenant("acme", "Acme Corp", "FREE", 3));
            Tenant globex = tenantRepository.save(new Tenant("globex", "Globex Corporation", "PRO", -1));

            // Initialize users
            String hashedPassword = passwordEncoder.encode("password");

            userRepository.save(new User("admin@acme.test", hashedPassword, "ADMIN", acme.getId()));
            userRepository.save(new User("user@acme.test", hashedPassword, "MEMBER", acme.getId()));
            userRepository.save(new User("admin@globex.test", hashedPassword, "ADMIN", globex.getId()));
            userRepository.save(new User("user@globex.test", hashedPassword, "MEMBER", globex.getId()));

            System.out.println("Test data initialized successfully!");
        }
    }
}
