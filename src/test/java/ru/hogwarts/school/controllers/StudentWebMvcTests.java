package ru.hogwarts.school.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentWebMvcTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private StudentService studentService;
    @SpyBean
    private AvatarService avatarService;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private StudentController studentController;
    @InjectMocks
    private FacultyController facultyController;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void getStudent() throws Exception {
        Long id = 1L;
        String name = "testStudent";
        int age = 22;

        Student student2 = new Student();
        student2.setId(id);
        student2.setName(name);
        student2.setAge(age);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void createStudent() throws Exception {
        Long id = 1L;
        String name = "testStudent";
        int age = 22;

        JSONObject student1 = new JSONObject();
        student1.put("name", name);
        student1.put("age", age);

        Student student2 = new Student();
        student2.setId(id);
        student2.setName(name);
        student2.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student2);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(student1.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void deleteStudent() throws Exception {
        Long id = 1L;
        String name = "testStudent";
        int age = 22;

        Student student2 = new Student();
        student2.setId(id);
        student2.setName(name);
        student2.setAge(age);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student2));
        doNothing().when(studentRepository).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findStudentByAge() throws Exception {
        long id = 100L;
        String name = "testStudent";
        int age = 22;

        Student student2 = new Student();
        student2.setId(id);
        student2.setName(name);
        student2.setAge(age);

        when(studentRepository.getAllByAge(22)).thenReturn(List.of(student2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age/22")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByAgeBetween() throws Exception {
        Long id = 1L;
        String name = "testStudent";
        int age = 22;
        Long id2 = 2L;
        String name2 = "testStudent2";
        int age2 = 32;


        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        Student student2 = new Student();
        student2.setId(id2);
        student2.setName(name2);
        student2.setAge(age2);

        when(studentRepository.findAllByAgeBetween(20, 35)).thenReturn(List.of(student, student2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age-between?min=20&max=35")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].age").value(age))
                .andExpect(jsonPath("$[1].id").value(id2))
                .andExpect(jsonPath("$[1].name").value(name2))
                .andExpect(jsonPath("$[1].age").value(age2))
        ;
    }

    @Test
    public void getFacultyByStudentId() throws Exception {
        Long id = 1L;
        String name = "testStudent";
        int age = 22;

        Faculty faculty = new Faculty();
        faculty.setId(10L);
        faculty.setName("Ravenclaw");
        faculty.setColor("blue");


        Student student2 = new Student();
        student2.setId(id);
        student2.setName(name);
        student2.setAge(age);
        student2.setFaculty(faculty);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty-by-student-id?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.name").value("Ravenclaw"))
                .andExpect(jsonPath("$.color").value("blue"));
    }
}