package com.notessaas.service;

import com.notessaas.config.JwtUtil;
import com.notessaas.dto.LoginRequest;
import com.notessaas.dto.LoginResponse;
import com.notessaas.model.Tenant;
import com.notessaas.model.User;
import com.notessaas.repository.TenantRepository;
import com.notessaas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        Tenant tenant = tenantRepository.findById(user.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        String token = jwtUtil.generateToken(user.getEmail(), user.getTenantId(), user.getRole());

        return new LoginResponse(token, user.getEmail(), user.getRole(),
                user.getTenantId(), tenant.getName(), tenant.getPlan());
    }
}
