package com.notessaas.service;


import com.notessaas.model.Tenant;
import com.notessaas.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public Tenant upgradeTenant(String slug) {
        Tenant tenant = tenantRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        tenant.setPlan("PRO");
        tenant.setMaxNotes(-1); // Unlimited

        return tenantRepository.save(tenant);
    }

    public Tenant findBySlug(String slug) {
        return tenantRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
    }
}

