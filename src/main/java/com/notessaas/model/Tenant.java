package com.notessaas.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "tenants")
public class Tenant {
    @Id
    private String id;

    @Indexed(unique = true)
    private String slug;

    private String name;
    private String plan; // FREE, PRO
    private int maxNotes;

    // Constructors
    public Tenant() {}

    public Tenant(String slug, String name, String plan, int maxNotes) {
        this.slug = slug;
        this.name = name;
        this.plan = plan;
        this.maxNotes = maxNotes;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }

    public int getMaxNotes() { return maxNotes; }
    public void setMaxNotes(int maxNotes) { this.maxNotes = maxNotes; }
}