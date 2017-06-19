package io.github.alicankustemur.todoapp.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.alicankustemur.todoapp.controller.EmployeeController;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.repository.EmployeeRepository;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import io.github.alicankustemur.todoapp.service.InitializeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InitializeService initializeService;

    @MockBean
    private EmployeeService service;

    @MockBean
    private EmployeeRepository repository;

    private final static String APPLICATION_URL = "http://localhost:3001/employee";


    // Feature : Employees Operations
    // Scenario : User want to add a new employee
    @Test
    public void givenEmployeeWhenSendPostRequestWithEmployeeThenAddNewEmployee() throws Exception {

        when(service.isNotItAvailableByIdentity(getEmployee().getIdentity())).thenReturn(true);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post(APPLICATION_URL + "/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(getEmployee())))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(handler().methodName("add"));

    }

    // Feature : Employees Operations
    // Scenario : User want to add a new employee but same employee identity available.
    @Test
    public void givenEmployeeWhenSendPostRequestWithEmployeeThenGetBadRequest() throws Exception {

        when(service.isNotItAvailableByIdentity(getEmployee().getIdentity())).thenReturn(false);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post(APPLICATION_URL + "/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(getEmployee())))
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(handler().methodName("add"));

    }

    // Feature : Employees Operations
    // Scenario : User want to show employee list
    @Test
    public void givenEmployeesWhenGoToListUrlThenGetEmployeeList() throws Exception {
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee());
        list.add(new Employee());
        list.add(new Employee());

        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get(APPLICATION_URL + "/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(handler().methodName("list"));

        verify(service, times(1)).getAll();

    }


    // Feature : Employees Operations
    // Scenario : User want to delete a employee
    @Test
    public void givenEmployeeIdWhenSendDeleteRequestWithIdThenDeleteThisEmployee() throws Exception {

        when(service.get(1L)).thenReturn(getEmployee());

        mockMvc.perform(delete(APPLICATION_URL + "/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(handler().methodName("delete"));


    }

    // Feature : Employees Operations
    // Scenario : User want to update a employee and
    @Test
    public void givenEmployeeIdAndEmployeeWhenSendPutRequesWithEmployeeIdAndEmployeeThenUpdateEmployeeWhoseIdIsThisEmployeeId() throws Exception {

        when(service.get(999999999L)).thenReturn(getEmployee());
        when(service.isNotItAvailableByIdentity(getAnotherEmployee().getIdentity())).thenReturn(true);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put(APPLICATION_URL + "/update/{id}", 999999999L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(getAnotherEmployee())))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(handler().methodName("update"));

    }

    // Feature : Employees Operations
    // Scenario : User want to update a employee but same employee identity available.
    @Test
    public void givenEmployeeIdAndEmployeeWhenSendPutRequesWithEmployeeIdAndEmployeeThenGetBadRequest() throws Exception {

        when(service.get(999999999L)).thenReturn(getEmployee());
        when(service.isNotItAvailableByIdentity(getAnotherEmployee().getIdentity())).thenReturn(false);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put(APPLICATION_URL + "/update/{id}", 999999999L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(getAnotherEmployee())))
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(handler().methodName("update"));

    }

    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(999999999L);
        employee.setName("Ali Can");
        employee.setSurname("Kuştemur");
        employee.setSalary(33f);
        employee.setIdentity(999L);

        return employee;
    }

    public Employee getAnotherEmployee() {
        Employee employee = new Employee();
        employee.setId(null);
        employee.setName("Özcan");
        employee.setSurname("Kuştemur");
        employee.setSalary(33f);
        employee.setIdentity(888L);

        return employee;
    }


}