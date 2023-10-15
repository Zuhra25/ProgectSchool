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
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyWebMvcTests {
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
    void getFaculty() throws Exception {
        long id = 1L;
        String name = "testFaculty";
        String color = "testColor";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }


    @Test
    public void facultyGetByColorTest() throws Exception {
        Long id = 3L;
        String name = "testFaculty";
        String color = "testColor";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.getAllByColor("testColor")).thenReturn(List.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/color/testColor")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void createFaculty() throws Exception {
        Long id = 1L;
        String name = "testFaculty";
        String color = "testColor";

        JSONObject faculty1 = new JSONObject();
        faculty1.put("name", name);
        faculty1.put("color", color);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id);
        faculty2.setName(name);
        faculty2.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty2);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(faculty1.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
    @Test
    void deleteFaculty() throws Exception{
        Long id = 1L;
        String name = "testFaculty";
        String color  = "testColor";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
        doNothing().when(facultyRepository).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void findFacultyByColor() throws Exception{
        Long id = 1L;
        String name = "testFaculty";
        String color  = "testColor";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findFacultyByColor(color)).thenReturn(List.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/color/black")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


//    @Test
//    public void facultyGetByColorOrNameTest() throws Exception {
//        Long id = 1L;
//        String name = "testStudent";
//        String color = "testColor";
//
//        Faculty faculty2 = new Faculty();
//        faculty2.setId(id);
//        faculty2.setName(name);
//        faculty2.setColor(color);
//        when(facultyRepository.getAllByNameIgnoreCase(name)).thenReturn(List.of(faculty2));
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/faculty/name-or-color?param=testStudent")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id").value(id))
//                .andExpect(jsonPath("$[0].name").value(name))
//                .andExpect(jsonPath("$[0].color").value(color));
//    }
//    @Test
//    public void getStudentByFacultyId() throws Exception {
//        List<Student> STUDENTLIST = new ArrayList<>(List.of(STUDENT_3,STUDENT_4));
//        when(studentRepository.findByFacultyId(3L)).thenReturn(STUDENTLIST);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/faculty/get-student-by-faculty-id?id=3")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(3L))
//                .andExpect(jsonPath("$[1].name").value("Дурин"))
//                .andExpect(jsonPath("$[0].age").value(62));
//    }


}






