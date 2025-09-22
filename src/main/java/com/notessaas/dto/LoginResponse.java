package com.notessaas.dto;


public class LoginResponse {
    private String token;
    private String email;
    private String role;
    private String tenantId;
    private String tenantName;
    private String plan;

    public LoginResponse(String token, String email, String role, String tenantId, String tenantName, String plan) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.plan = plan;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
}
