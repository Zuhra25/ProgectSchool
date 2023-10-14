package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controllers.FacultyController;
import ru.hogwarts.school.controllers.StudentController;

import static org.assertj.core.api.Assertions.assertThat;

class SchoolApplicationTests {
}

//import org.junit.jupiter.api.Assertions;
//        import org.junit.jupiter.api.Test;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.test.context.SpringBootTest;
//        import org.springframework.boot.test.web.client.TestRestTemplate;
//        import org.springframework.boot.test.web.server.LocalServerPort;
//        import ru.hogwarts.school.controllers.FacultyController;
//        import ru.hogwarts.school.controllers.StudentController;
//
//        import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class StudentControllerTests {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private StudentController studentController;
//    @Autowired
//    private FacultyController facultyController;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    public StudentControllerTests() {
//    }
//
//    @Test
//    public void contextLoads() throws Exception {
//        Assertions.assertNotNull(studentController);
//    }
//
//    @Test    //получить инф о студенте по ID
//    public void testGetStudent() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/2", String.class))
//                .isEqualTo("{\"id\":2,\"name\":\"Ron Weasley\",\"age\":9,\"faculty\":{\"id\":9,\"name\":\"Gryffindor\",\"color\":\"red\",\"studentList\":[]}}");
//    }
//
//    @Test    //получить инф о студенте по возрасту
//    public void testFindStudentByAge() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/15", String.class))
//                .isEqualTo("[{\"id\":8,\"name\":\"Harry Potter\",\"age\":15,\"faculty\":{\"id\":9,\"name\":\"Gryffindor\",\"color\":\"red\",\"studentList\":[]}}]");
//    }
//
//    @Test    //получить инф о студенте по интервалу возвраста
//    public void testFindByAgeBetween() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age-between/?min=10&max=22", String.class))
//                .isEqualTo("[{\"id\":8,\"name\":\"Harry Potter\",\"age\":15,\"faculty\":{\"id\":9,\"name\":\"Gryffindor\",\"color\":\"red\",\"studentList\":[]}},{\"id\":9,\"name\":\"Drago Malfoy\",\"age\":21,\"faculty\":{\"id\":10,\"name\":\"Slytherin\",\"color\":\"green\",\"studentList\":[]}}]");
//    }
//
//    @Test    //получить инф о факультете по ID студента
//    public void testGetFacultyByStudentId() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty-by-student-id?id=9", String.class))
//                .isEqualTo("{\"id\":10,\"name\":\"Slytherin\",\"color\":\"green\",\"studentList\":[]}");
//    }
//
//
//}
