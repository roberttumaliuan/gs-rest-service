// Original test class submission for Task 4: Unit Testing, based on Spring unit testing tutorial
import com.example.restservice.controllers.EmployeeController;
import com.example.restservice.entities.Employee;
import com.example.restservice.repo.EmployeeManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
@ContextConfiguration(classes = {EmployeeController.class})
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    EmployeeManager employeeManager;

    Employee employee1 = new Employee(1, "Rayven", "Yor", "rayvenyor@gmail.com", "Mr.");
    Employee employee2 = new Employee(2, "David", "Landup", "dlandup@yahoo.com", "Mr.");
    Employee employee3 = new Employee(3, "Jane", "Doe", "janedoe@hotmail.com", "Mrs.");

    @Test
    public void getAllEmployees_success() throws Exception {
        List<Employee> records = new ArrayList<>(Arrays.asList(employee1, employee2, employee3));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void createRecord_success() throws Exception{
        Employee employee4 = new Employee(1, "Nu", "Em", "nuem@gmail.com", "Mrs.");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(employee4));
    }
}

