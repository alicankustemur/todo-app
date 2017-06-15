package io.github.alicankustemur.todoapp.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.alicankustemur.todoapp.Application;
import io.github.alicankustemur.todoapp.controller.DepartmentController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import io.github.alicankustemur.todoapp.service.InitializeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InitializeService initializeService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DepartmentService service;

    private final static String APPLICATION_URL = "http://localhost:3001/department";

    // Feature : Department Operations
    // Scenario : User want to add a department
    @Test
    public void givenDepartmentInformationsAndOneEmployeeWhenSendPostRequestWithDepartmentThenAddNewDepartment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post(APPLICATION_URL + "/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(getDepartment())))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepartmentController.class))
                .andExpect(handler().methodName("add"));

    }

    // Feature : Department Operations
    // Scenario : User want to show department list
    @Test
    public void givenDepartmentsWhenGoToListUrlThenGetDepartmentList() throws Exception {
        List<Department> list = new ArrayList<Department>();
        list.add(new Department());
        list.add(new Department());
        list.add(new Department());

        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get(APPLICATION_URL + "/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepartmentController.class))
                .andExpect(handler().methodName("list"));

    }


    // Feature : Department Operations
    // Scenario : User want to delete a department
    @Test
    public void givenDepartmentIdWhenSendDeleteRequestWithIdThenDeleteThisDepartment() throws Exception {

        when(service.get(1L)).thenReturn(getDepartment());

        mockMvc.perform(delete(APPLICATION_URL + "/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepartmentController.class))
                .andExpect(handler().methodName("delete"));
    }

    // Feature : Department Operations
    // Scenario : User want to update a department
    @Test
    public void givenDepartmentIdAndDepartmentWhenSendPutRequesWithDepartmentIdAndEmployeeThenUpdateDepartmentWhoseIdIsThisDepartmentId() throws Exception {

        when(employeeService.get(1L)).thenReturn(getDepartment().getEmployee());
        when(service.isItAvailableDepartmentWithThisEmployee(getDepartment().getEmployee())).thenReturn(false);
        when(service.get(1L)).thenReturn(getDepartment());

        Department updatedDepartment = getDepartment();
        updatedDepartment.setName("Goblins");
        updatedDepartment.setRecordUpdateTime(new Date());

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put(APPLICATION_URL + "/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(updatedDepartment)))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepartmentController.class))
                .andExpect(handler().methodName("update"));

    }


    public Department getDepartment() {
        Employee employee = new Employee();
        employee.setId(1000000L);
        employee.setName("Frodo");
        employee.setSurname("Baggins");
        employee.setSalary(15f);

        Department department = new Department();
        department.setId(100000L);
        department.setName("Baggins");
        department.setDescription("The Baggins clan traced their origin to the first recorded Baggins, one Balbo Baggins, who was born in or near Hobbiton in S.R. 1167.");
        department.setRecordCreateTime(new Date());
        department.setEmployee(employee);

        return department;
    }

    public Department getAnotherDepartment() {
        Employee employee = new Employee();
        employee.setId(999L);
        employee.setName("Frodo");
        employee.setSurname("Baggins");
        employee.setSalary(15f);

        Department department = new Department();
        department.setId(999L);
        department.setName("Elfs");
        department.setDescription("The Baggins clan traced their origin to the first recorded Baggins, one Balbo Baggins, who was born in or near Hobbiton in S.R. 1167.");
        department.setRecordCreateTime(new Date());
        department.setEmployee(employee);

        return department;
    }


}