package ru.hogwarts.school.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.QueryByStudent;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/query-by-student")
public class QueryByStudentController {
    private final StudentService studentService;

    public QueryByStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/count-student")
    public Integer getCountStudent() {
        return studentService.getCountStudent();
    }

    @GetMapping("/avg-age-student")
    public Double getAvgAgeStudent() {
        return studentService.getAvgAgeStudent();
    }

    @GetMapping("/last-five-student")
    public List<QueryByStudent> getLastFiveStudent() {
        return studentService.getLastFiveStudent();
    }
}
