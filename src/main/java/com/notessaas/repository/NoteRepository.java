package com.notessaas.repository;

import com.notessaas.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByTenantIdOrderByCreatedAtDesc(String tenantId);
    Optional<Note> findByIdAndTenantId(String id, String tenantId);
    long countByTenantId(String tenantId);
    void deleteByIdAndTenantId(String id, String tenantId);
}
