package ru.hogwarts.school.service;


import liquibase.pro.packaged.L;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("вызван метод addStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("вызван метод findStudent");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("вызван метод editStudent)");
        Student temp = studentRepository.findById(student.getId()).get();
        temp.setName(student.getName());
        temp.setAge(student.getAge());
        return studentRepository.save(temp);
    }

    public void deleteStudent(long id) {
        logger.info("вызван метод deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudent() {
        logger.info("вызван метод getAllStudent");
        return studentRepository.findAll();
    }

    public List<Student> findStudentByAge(int age) {
        logger.info("вызван метод findStudentByAge");
        if (age > 0) {
            return getAllStudent().stream()
                    .filter(student -> student.getAge() == age)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("вызван метод findByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Faculty getFacultyByStudentId(long id) {
        logger.info("вызван метод getFacultyByStudentId");
        return studentRepository.findById(id).get().getFaculty();
    }

    public List<Student> getByFacultyId(long id) {
        logger.info("вызван метод getByFacultyId");
        return studentRepository.findByFacultyId(id);
    }

    public Integer getCountStudent() {
        logger.info("вызван метод getCountStudent");
        return studentRepository.getCountStudent();
    }

    public Double getAvgAgeStudent() {
        logger.info("вызван метод getAvgAgeStudent");
        return studentRepository.getAvgAgeStudent();
    }

    public List<QueryByStudent> getLastFiveStudent() {
        logger.info("вызван метод getLastFiveStudent");
        return studentRepository.getLastFiveStudent();
    }

    public List<String> staringWithLetter(String startLetter) {
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .filter((name -> name.startsWith(startLetter.toUpperCase())))
                .sorted()
                .collect(Collectors.toList());
    }

    public Double avgAgeStudent() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
    }
}
