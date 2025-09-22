package com.notessaas.dto;


import jakarta.validation.constraints.NotBlank;

public class NoteRequest {
    @NotBlank
    private String title;

    private String content;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
