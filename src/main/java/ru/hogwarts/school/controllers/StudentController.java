package ru.hogwarts.school.controllers;


import liquibase.pro.packaged.I;
import liquibase.pro.packaged.S;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        studentService.editStudent(student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> findStudentByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.findStudentByAge(age));
    }

    @GetMapping("/age-between")
    public List<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/faculty-by-student-id")
    public Faculty getFacultyByStudentId(@RequestParam long id) {
        return studentService.getFacultyByStudentId(id);
    }

    @GetMapping("/staring-with-letter")
    public List<String> staringWithLetter(@RequestParam String startLetter) {
        return studentService.staringWithLetter(startLetter);
    }

    @GetMapping("/avg-age-of-all-students")
    public Double avgAgeStudent() {
        return studentService.avgAgeStudent();
    }

    @GetMapping("/get-all-name-in-console")
    public void getAllNameInConsoleWithThread() {
        studentService.getAllNameInConsoleWithThread();
    }
    @GetMapping("/get-all-name-in-console-sync")
    public void getAllNameInConsoleWithThreadSync() {
        studentService.getAllNameInConsoleWithThreadSync();
    }

}
