package org.jfdeveloper.notesapp.controller;

import org.jfdeveloper.notesapp.entity.Note;
import org.jfdeveloper.notesapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository repository;


    @GetMapping
    private List<Note> getNotes(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Note getANote(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()->new ResponseStatusException((HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note newNote){
        return new ResponseEntity<>(repository.save(newNote), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Note updateNote(@PathVariable Long id, @RequestBody Note updates){
        Note note=repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getTitle()!=null) note.setTitle(updates.getTitle());
        if (updates.getContent()!=null) note.setContent(updates.getContent());
        if (updates.getCompleted()!=null) note.setCompleted(updates.getCompleted());

        return repository.save(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> trashNote(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
