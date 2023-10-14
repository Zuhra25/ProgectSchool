package ru.hogwarts.school.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    public StudentControllerTests() {
    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(studentController);
    }

    // StudentController
    @Test    //создание тестового студента
    public void testCreateStudent() throws Exception {
        Student resultStudent = new Student();
        resultStudent.setName("testStudent");
        resultStudent.setAge(22);

        Student expectedStudent = new Student();
        expectedStudent.setName("testStudent");
        expectedStudent.setAge(22);

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", resultStudent, Student.class))
                .isNotNull();
        Assertions.assertEquals(resultStudent.getName(), expectedStudent.getName());
        Assertions.assertEquals(resultStudent.getAge(), expectedStudent.getAge());

    }

    @Test    //удаление студента
    public void testDeleteStudent() {
        Student student = new Student();
        student.setName("testStudent");
        student.setAge(22);

        Student postStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        this.restTemplate.delete("http://localhost:" + port + "/student/" + postStudent.getId(), Student.class);
        Student result = this.restTemplate.getForObject("http://localhost:" + port + "/student/" + postStudent.getId(), Student.class);

        Assertions.assertEquals(result.toString(), "Student{id=0, name='null', age=0}");
    }


    @Test    //получение тестового студента
    public void testGetStudent() throws Exception {
        Student resultStudent = new Student();
        resultStudent.setName("testStudent");
        resultStudent.setAge(22);

        Student expected = this.restTemplate.postForObject("http://localhost:" + port + "/student", resultStudent, Student.class);
        Student result = this.restTemplate.getForObject("http://localhost:" + port + "/student/" + expected.getId(), Student.class);
        Assertions.assertEquals(result, expected);
    }


    @Test    ////получить инф о студенте по ID
    public void testGetStudentById() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty-by-student-id?id=8", String.class))
                .isEqualTo("{\"id\":9,\"name\":\"Gryffindor\",\"color\":\"red\",\"studentList\":[]}");
    }

    @Test    //получить инф о студентах по возрасту
    public void testFindStudentByAge() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/15", String.class))
                .isEqualTo("[{\"id\":8,\"name\":\"Harry Potter\",\"age\":15,\"faculty\":{\"id\":9,\"name\":\"Gryffindor\",\"color\":\"red\",\"studentList\":[]}}]");
    }

    @Test    //получить инф о студенте по интервалу возвраста
    public void testFindByAgeBetween() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age-between/?min=10&max=21", String.class))
                .isEqualTo("[{\"id\":8,\"name\":\"Harry Potter\",\"age\":15,\"faculty\":{\"id\":9,\"name\":\"Gryffindor\",\"color\":\"red\",\"studentList\":[]}},{\"id\":9,\"name\":\"Drago Malfoy\",\"age\":21,\"faculty\":{\"id\":10,\"name\":\"Slytherin\",\"color\":\"green\",\"studentList\":[]}}]");
    }

    @Test    //получить инф о факультете по ID студента
    public void testGetFacultyByStudentId() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty-by-student-id?id=9", String.class))
                .isEqualTo("{\"id\":10,\"name\":\"Slytherin\",\"color\":\"green\",\"studentList\":[]}");
    }

    //FacultyController
    @Test
    public void contextLoadsFaculty() throws Exception {
        Assertions.assertNotNull(facultyController);
    }
    @Test    //получение тестового факультета
    public void testGetFaculty() throws Exception {
        Faculty resultFaculty = new Faculty();
        resultFaculty.setName("testFaculty");
        resultFaculty.setColor("black");

        Faculty expected = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", resultFaculty, Faculty.class);
        Faculty result = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + expected.getId(), Faculty.class);
        Assertions.assertEquals(result, expected);
    }
    @Test    //удаление факультета
    public void testDeleteFaculty() {
        Faculty resultFaculty = new Faculty();
        resultFaculty.setName("testFaculty");
        resultFaculty.setColor("black");


        Faculty postFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", resultFaculty, Faculty.class);
        this.restTemplate.delete("http://localhost:" + port + "/faculty/" + postFaculty.getId(), Faculty.class);
        Faculty result = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + postFaculty.getId(), Faculty.class);

        Assertions.assertEquals(result.toString(), "Faculty{id=0, name='null', color='null'}");
    }

    @Test    ////получить инф о факультете по цвету
    public void testFindFacultyByColor() throws Exception {
        Faculty resultFaculty = new Faculty();
        resultFaculty.setName("testFaculty");
        resultFaculty.setColor("black");

        var result = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/color/black", String.class);
        assertThat(result.contains("\"testFaculty\",\"color\":\"black\"")).isTrue();
    }

    @Test    //получить инф о студенте по факультету
    public void testGetStudentsByFacultyId() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/student-by-faculty-id?id=10", String.class))
                .isEqualTo("[{\"id\":9,\"name\":\"Drago Malfoy\",\"age\":21,\"faculty\":{\"id\":10,\"name\":\"Slytherin\",\"color\":\"green\",\"studentList\":[]}}]");
    }
}
