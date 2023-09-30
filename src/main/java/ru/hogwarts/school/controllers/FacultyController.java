package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        facultyService.editFaculty(faculty);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Faculty>> findFacultyByColor(@PathVariable String color) {
        return ResponseEntity.ok(facultyService.findFacultyByColor(color));
    }
}
