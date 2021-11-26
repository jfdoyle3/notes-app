package org.jfdeveloper.notesapp.repository;

import org.jfdeveloper.notesapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
