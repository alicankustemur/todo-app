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

import io.github.alicankustemur.todoapp.Application;
import io.github.alicankustemur.todoapp.controller.DepartmentController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DepartmentControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private EmployeeService employeeService;
	
	@Mock
	private DepartmentService service;

	@InjectMocks
	@Autowired
	private DepartmentController controller;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	
	@Test
	public void testGetAllDepartments() throws Exception {
		List<Department> list = new ArrayList<Department>();
		list.add(new Department());
		list.add(new Department());
		
		when(service.getAll()).thenReturn(list);
		
		mockMvc.perform(get("/department/departments")
		   .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(jsonPath("$.*", hasSize(2)))
	       .andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteDepartment() throws Exception{
		Department department = new Department();
		department.setId(1L);
		department.setName("Department 1");
		
		when(service.get(1L)).thenReturn(department);
		
		mockMvc.perform(get("/department/delete/{id}",1L))
						.andExpect(status().is3xxRedirection())
				        .andExpect(view().name("redirect:/department"))
				        .andExpect(handler().handlerType(DepartmentController.class))
				        .andExpect(handler().methodName("deleteDepartment"))
				        .andReturn();
	}
	
	@Test
	public void testAddDepartment() throws Exception{
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		
		when(employeeService.get(1L)).thenReturn(employee);
		when(service.isItAvailableDepartmentWithThisEmployee(employee)).thenReturn(false);
		
		mockMvc.perform(post("/department/add")
				.param("name", "Department 1")
				.param("description", "Lorem ipsum dolor sit amet")
				.param("employee", "1"))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/department"))
		        .andExpect(handler().handlerType(DepartmentController.class))
		        .andExpect(handler().methodName("addDepartment"))
		        .andReturn();
		
	}
	
	@Test
	public void testAddDepartmentError() throws Exception{
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		
		when(employeeService.get(1L)).thenReturn(employee);
		when(service.isItAvailableDepartmentWithThisEmployee(employee)).thenReturn(true);
		
		mockMvc.perform(post("/department/add")
				.param("name", "Department 1")
				.param("description", "Lorem ipsum dolor sit amet")
				.param("employee", "1"))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/department?error=availableDepartment"))
		        .andExpect(handler().handlerType(DepartmentController.class))
		        .andExpect(handler().methodName("addDepartment"))
		        .andReturn();
		
	}
	
	@Test
	public void testUpdateDepartment() throws Exception{
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		
		Department department = new Department();
		department.setId(1L);
		
		when(employeeService.get(1L)).thenReturn(employee);
		when(service.isItAvailableDepartmentWithThisEmployee(employee)).thenReturn(false);
		when(service.get(1L)).thenReturn(department);
		
		mockMvc.perform(post("/department/update")
				.param("id", "1")
				.param("name", "Department 1")
				.param("description", "Lorem ipsum dolor sit amet")
				.param("employee", "1"))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/department"))
		        .andExpect(handler().handlerType(DepartmentController.class))
		        .andExpect(handler().methodName("updateDepartment"))
		        .andReturn();
		
	}
	
	@Test
	public void testUpdateDepartmentError() throws Exception{
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		
		Department department = new Department();
		department.setId(1L);
		
		when(employeeService.get(1L)).thenReturn(employee);
		when(service.isItAvailableDepartmentWithThisEmployee(employee)).thenReturn(true);
		
		mockMvc.perform(post("/department/update")
				.param("id", "1")
				.param("name", "Department 1")
				.param("description", "Lorem ipsum dolor sit amet")
				.param("employee", "1"))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/department?error=availableDepartment"))
		        .andExpect(handler().handlerType(DepartmentController.class))
		        .andExpect(handler().methodName("updateDepartment"))
		        .andReturn();
		
	}
	
	
}