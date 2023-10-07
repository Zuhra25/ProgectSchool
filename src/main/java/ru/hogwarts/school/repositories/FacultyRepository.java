package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Set;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Set<Faculty> findByColorContainsIgnoreCase(String name);
    Set<Faculty> findByNameContainsIgnoreCase(String name);
}
