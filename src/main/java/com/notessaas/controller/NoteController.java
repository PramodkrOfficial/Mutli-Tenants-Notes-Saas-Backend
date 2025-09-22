package com.notessaas.controller;

import com.notessaas.config.UserPrincipal;
import com.notessaas.dto.NoteRequest;
import com.notessaas.model.Note;
import com.notessaas.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody NoteRequest request, Authentication auth) {
        try {
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();
            Note note = noteService.createNote(request, user.getEmail(), user.getTenantId());
            return ResponseEntity.ok(note);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(Authentication auth) {
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        List<Note> notes = noteService.getNotesByTenant(user.getTenantId());
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable String id, Authentication auth) {
        try {
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();
            Note note = noteService.getNoteById(id, user.getTenantId());
            return ResponseEntity.ok(note);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @Valid @RequestBody NoteRequest request, Authentication auth) {
        try {
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();
            Note note = noteService.updateNote(id, request, user.getTenantId());
            return ResponseEntity.ok(note);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id, Authentication auth) {
        try {
            UserPrincipal user = (UserPrincipal) auth.getPrincipal();
            noteService.deleteNote(id, user.getTenantId());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

