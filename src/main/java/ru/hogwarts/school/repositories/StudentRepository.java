package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.QueryByStudent;
import ru.hogwarts.school.model.Student;


import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    public List<Student> getAllByAge(int age);
    public List<Student> findAllByAgeBetween(int min,int max);
    public List<Student> findByFacultyId(Long facultyId);

    @Query(value = "SELECT COUNT(*) FROM student",nativeQuery = true)
    Integer getCountStudent();

    @Query(value = "SELECT avg(age) FROM student",nativeQuery = true)
    Double getAvgAgeStudent();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5",nativeQuery = true)
    List<QueryByStudent> getLastFiveStudent();
}




