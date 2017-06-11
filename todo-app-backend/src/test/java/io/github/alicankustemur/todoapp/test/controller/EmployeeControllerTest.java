package io.github.alicankustemur.todoapp.test.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import io.github.alicankustemur.todoapp.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.github.alicankustemur.todoapp.controller.EmployeeController;
import io.github.alicankustemur.todoapp.repository.EmployeeRepository;
import io.github.alicankustemur.todoapp.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private EmployeeService service;
	
	@Mock
	private EmployeeRepository repository;

	@InjectMocks
	@Autowired
	private EmployeeController controller;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testIndex() throws Exception {

		mockMvc.perform(get("/employee"))
			.andExpect(status().isOk())
			.andExpect(view().name("employee"));
	}

	
	@Test
	public void testGetAllEmployees() throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee());
		list.add(new Employee());
		list.add(new Employee());
		
		when(service.getAll()).thenReturn(list);
		
		mockMvc.perform(get("/employee/employees")
		   .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(jsonPath("$.*", hasSize(3)))
	       .andExpect(status().isOk());
	}
	
	
	@Test
	public void testAddEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		
		mockMvc.perform(post("/employee/add")
				.param("name", employee.getName())
				.param("surname", employee.getSurname())
				.param("salary", employee.getSalary().toString()))
				.andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employee"))
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(handler().methodName("addEmployee"))
                .andReturn();
	}
	
	@Test
	public void testUpdateEmployee() throws Exception{
		
		Employee employee = new Employee();
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		
		when(service.get(1L)).thenReturn(employee);
		
		mockMvc.perform(post("/employee/update")
				.param("id", "1")
				.param("name", employee.getName())
				.param("surname", employee.getSurname())
				.param("salary",employee.getSalary().toString()))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/employee"))
		        .andExpect(handler().handlerType(EmployeeController.class))
		        .andExpect(handler().methodName("updateEmployee"))
		        .andReturn();
	}
	
	@Test
	public void testDeleteEmployee() throws Exception{
		
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		
		when(service.get(1L)).thenReturn(employee);
		
		mockMvc.perform(get("/employee/delete/{id}", 1L))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/employee"))
		        .andExpect(handler().handlerType(EmployeeController.class))
		        .andExpect(handler().methodName("deleteEmployee"))
		        .andReturn();
	}
	

}