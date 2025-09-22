package com.notessaas.controller;


import com.notessaas.config.UserPrincipal;
import com.notessaas.model.Tenant;
import com.notessaas.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/{slug}/upgrade")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, String>> upgradeTenant(@PathVariable String slug, Authentication auth) {
        try {
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();
            Tenant tenant = tenantService.findBySlug(slug);

            // Ensure admin can only upgrade their own tenant
            if (!tenant.getId().equals(user.getTenantId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }

            tenantService.upgradeTenant(slug);
            return ResponseEntity.ok(Map.of("message", "Tenant upgraded successfully", "plan", "PRO"));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

