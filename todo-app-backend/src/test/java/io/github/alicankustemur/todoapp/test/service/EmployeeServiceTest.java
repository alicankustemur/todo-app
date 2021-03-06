package io.github.alicankustemur.todoapp.test.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

import java.util.Date;

import io.github.alicankustemur.todoapp.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.alicankustemur.todoapp.repository.EmployeeRepository;
import io.github.alicankustemur.todoapp.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository repository;

	@InjectMocks
	@Autowired
	private EmployeeService service;

	@Test
	public void givenEmployeeWhenAddThenReturnEmployee() throws Exception {

		// Given
		Employee employee = getEmployee();
		when(repository.findOne(employee.getId())).thenReturn(null);
		when(repository.save(employee)).thenReturn(employee);

		// When
		Employee gettingEmployee = service.saveOrUpdate(employee);

		// Then
		assertThat(gettingEmployee.getId(), is(equalTo(employee.getId())));
	}
	
	@Test	
	public void givenEmployeeWhenRecordIsDeletedTrueThenReturnNull() throws Exception{

		// Given
		Employee employee = getEmployee();
		when(repository.findOne(employee.getId())).thenReturn(null);
		when(repository.save(employee)).thenReturn(employee);

		// When
		Employee gettingEmployee = service.saveOrUpdate(employee);
		gettingEmployee.setRecordIsDeleted(true);
		gettingEmployee.setRecordUpdateTime(new Date());
		service.saveOrUpdate(gettingEmployee);

		// Then
		Employee removedEmployee = service.get(gettingEmployee.getId());
		assertThat(removedEmployee, nullValue());

	}
	
	@Test	
	public void givenEmployeeWhenUpdateThenReturnUpdatedEmployee() throws Exception{

		// Given
		Employee employee = getEmployee();
		when(repository.findOne(employee.getId())).thenReturn(null);
		when(repository.save(employee)).thenReturn(employee);

		// When
		Employee gettingEmployee = service.saveOrUpdate(employee);
		gettingEmployee.setName("Özcan");;
		gettingEmployee.setRecordUpdateTime(new Date());
		Employee updatedEmployee = service.saveOrUpdate(gettingEmployee);

		// Then
		assertThat(updatedEmployee.getName(), is(equalTo("Özcan")));
		
	}
	

	public Employee getEmployee() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		employee.setRecordIsDeleted(false);
		employee.setRecordCreateTime(new Date());
		return employee;
	}

}
