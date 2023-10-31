package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    public FacultyService(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        Faculty temp = facultyRepository.findById(faculty.getId()).get();
        temp.setName(faculty.getName());
        temp.setColor(faculty.getColor());
        return facultyRepository.save(temp);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public List<Faculty> findFacultyByColor(String color) {
        if (color != null && !color.isBlank()) {
            return getAllFaculties().stream()
                    .filter(faculty -> faculty.getColor().equals(color))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Set<Faculty> findByColorOrName(String param) {
        Set<Faculty> temp = new HashSet<>();
        temp.addAll(facultyRepository.findByColorContainsIgnoreCase(param));
        temp.addAll(facultyRepository.findByNameContainsIgnoreCase(param));
        return temp;
    }

    public List<Student> getStudentsByFacultyId(Long id) {
        return studentService.getByFacultyId(id);
    }
}
