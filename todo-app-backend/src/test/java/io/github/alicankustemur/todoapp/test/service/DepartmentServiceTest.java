package io.github.alicankustemur.todoapp.test.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.repository.DepartmentRepository;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.alicankustemur.todoapp.domain.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {

	@Mock
	private DepartmentRepository repository;

	@InjectMocks
	@Autowired
	private DepartmentService service;

	@Test
	public void givenDepartmentWhenAddThenReturnDepartment() throws Exception {
		// Given
		Department department = getDepartment();
		when(repository.findOne(department.getId())).thenReturn(null);
		when(repository.save(department)).thenReturn(department);

		// When
		Department gettingDepartment = service.saveOrUpdate(department);

		// Then
		assertThat(gettingDepartment.getId(), is(equalTo(department.getId())));

		verify(repository, times(1)).save(department);
	}
	
	@Test	
	public void givenDepartmentWhenRecordIsDeletedTrueThenReturnNull() throws Exception{

		// Given
		Department department = getDepartment();
		when(repository.findOne(department.getId())).thenReturn(null);
		when(repository.save(department)).thenReturn(department);

		// When
		Department gettingDepartment = service.saveOrUpdate(department);
		gettingDepartment.setRecordIsDeleted(true);
		gettingDepartment.setRecordUpdateTime(new Date());
		service.saveOrUpdate(gettingDepartment);

		// Then
		Department removedDepartment = service.get(gettingDepartment.getId());
		assertThat(removedDepartment, nullValue());

		verify(repository, times(2)).save(department);
		verify(repository, times(1)).findOne(gettingDepartment.getId());
		
	}
	
	@Test	
	public void givenDepartmentWhenUpdateThenReturnUpdatedDepartment() throws Exception{

		// Given
		Department department = getDepartment();
		when(repository.findOne(department.getId())).thenReturn(null);
		when(repository.save(department)).thenReturn(department);

		// When
		Department gettingDepartment = service.saveOrUpdate(department);
		gettingDepartment.setName("Department 2");;
		gettingDepartment.setRecordUpdateTime(new Date());

		// Then
		Department updatedDepartment = service.saveOrUpdate(gettingDepartment);
		assertThat(updatedDepartment.getName(), is(equalTo("Department 2")));

		verify(repository, times(2)).save(department);
	}
	

	public Department getDepartment() {
		Department department = new Department();
		department.setId(1L);
		department.setName("Department 1");
		department.setRecordIsDeleted(false);
		department.setRecordCreateTime(new Date());
		
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Ali Can");
		employee.setSurname("Kuştemur");
		employee.setSalary(33f);
		employee.setRecordIsDeleted(false);
		employee.setRecordCreateTime(new Date());
		
		department.setEmployee(employee);
		
		return department;
	}

}
