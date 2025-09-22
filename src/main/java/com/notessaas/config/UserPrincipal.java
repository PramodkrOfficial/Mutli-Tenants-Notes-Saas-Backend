package com.notessaas.config;


public class UserPrincipal {
    private String email;
    private String tenantId;
    private String role;

    public UserPrincipal(String email, String tenantId, String role) {
        this.email = email;
        this.tenantId = tenantId;
        this.role = role;
    }

    // Getters
    public String getEmail() { return email; }
    public String getTenantId() { return tenantId; }
    public String getRole() { return role; }
}