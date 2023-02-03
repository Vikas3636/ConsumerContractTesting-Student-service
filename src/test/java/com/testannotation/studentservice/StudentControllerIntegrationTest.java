package com.testannotation.studentservice;

import com.testannotation.studentservice.model.Student;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {
    @LocalServerPort
    private int port = 8090;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private void testStudentRetrieval(int id) throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/student-service/student?ID=" + id),
                HttpMethod.GET, entity, String.class);

        String expected = "{" +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"Smart\"," +
                "\"dateOfBirth\":\"02/02/1997\"," +
                "\"registrationDate\":\"04/12/2009\"," +
                "\"address\":\"15 Foreshore Road, Philadelphia, PA, 19101\"," +
                "\"id\":\"2020091701\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private void testStudentCreation() {

        Student student = new Student("Billy", "Smart", "2020091702", "02/02/1997", "04/12/2009", "15 Foreshore Road, Philadelphia, PA, 19101");

        HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/student-service/student"),
                HttpMethod.POST, entity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        Assert.assertTrue(actual.contains("student-service/student?ID="));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void searchStudentWithID() throws JSONException {
        testStudentRetrieval(2020091701);
    }

    @Test
    public void addNewStudent() {
        testStudentCreation();
    }
}
