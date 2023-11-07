package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/name-or-color")
    public Set<Faculty> findByColorOrName(@RequestParam("param") String param) {
        return facultyService.findByColorOrName(param);
    }

    @GetMapping("/student-by-faculty-id")
    public List<Student> getStudentsByFacultyId(@RequestParam long id) {
        return facultyService.getStudentsByFacultyId(id);
    }
    @GetMapping("/longest-faculty-name")
    public List<String> longestFacultyName() {
        return facultyService.longestFacultyName();
    }
}
