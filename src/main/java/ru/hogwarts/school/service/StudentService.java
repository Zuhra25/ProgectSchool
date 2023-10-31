package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.QueryByStudent;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        Student temp = studentRepository.findById(student.getId()).get();
        temp.setName(student.getName());
        temp.setAge(student.getAge());
        return studentRepository.save(temp);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentByAge(int age) {
        if (age > 0) {
            return getAllStudent().stream()
                    .filter(student -> student.getAge() == age)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    public List<Student> findByAgeBetween(int min, int max) {
        return  studentRepository.findAllByAgeBetween(min,max);
    }

    public Faculty getFacultyByStudentId(long id) {
        return studentRepository.findById(id).get().getFaculty();
    }
    public List<Student> getByFacultyId (long id) {
        return studentRepository.findByFacultyId(id);
    }

    public Integer getCountStudent() {
        return studentRepository.getCountStudent();
    }
    public Double getAvgAgeStudent() {
        return studentRepository.getAvgAgeStudent();
    }
    public List<QueryByStudent> getLastFiveStudent() {
        return studentRepository.getLastFiveStudent();
    }
}
