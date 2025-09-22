package com.notessaas.service;


import com.notessaas.dto.NoteRequest;
import com.notessaas.model.Note;
import com.notessaas.model.Tenant;
import com.notessaas.repository.NoteRepository;
import com.notessaas.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public Note createNote(NoteRequest request, String userId, String tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        long currentNoteCount = noteRepository.countByTenantId(tenantId);

        if ("FREE".equals(tenant.getPlan()) && currentNoteCount >= tenant.getMaxNotes()) {
            throw new RuntimeException("Note limit reached. Upgrade to Pro for unlimited notes.");
        }

        Note note = new Note(request.getTitle(), request.getContent(), userId, tenantId);
        return noteRepository.save(note);
    }

    public List<Note> getNotesByTenant(String tenantId) {
        return noteRepository.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }

    public Note getNoteById(String id, String tenantId) {
        return noteRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public Note updateNote(String id, NoteRequest request, String tenantId) {
        Note note = getNoteById(id, tenantId);
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return noteRepository.save(note);
    }

    public void deleteNote(String id, String tenantId) {
        Note note = getNoteById(id, tenantId);
        noteRepository.delete(note);
    }
}

