package io.github.alicankustemur.todoapp.test.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
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
	public void testAddNewDepartment() throws Exception {
		Department department = getDepartment();
		when(repository.findOne(department.getId())).thenReturn(null);
		when(repository.save(department)).thenReturn(department);

		Department gettingDepartment = service.saveOrUpdate(department).orElse(new Department());

		assertThat(gettingDepartment.getId(), is(equalTo(department.getId())));
	}
	
	@Test	
	public void testRemoveDepartment() throws Exception{
		Department department = getDepartment();
		
		when(repository.findOne(department.getId())).thenReturn(null);
		when(repository.save(department)).thenReturn(department);

		Department gettingDepartment = service.saveOrUpdate(department).orElse(new Department());
		gettingDepartment.setRecordIsDeleted(true);
		gettingDepartment.setRecordUpdateTime(new Date());
		service.saveOrUpdate(gettingDepartment);
		
		Department removedDepartment = service.get(gettingDepartment.getId());

		assertThat(removedDepartment, nullValue());
		
	}
	
	@Test	
	public void testUpdateDepartment() throws Exception{
		Department department = getDepartment();
		
		when(repository.findOne(department.getId())).thenReturn(null);
		when(repository.save(department)).thenReturn(department);

		Department gettingDepartment = service.saveOrUpdate(department).orElse(new Department());
		gettingDepartment.setName("Department 2");;
		gettingDepartment.setRecordUpdateTime(new Date());
		Department updatedDepartment = service.saveOrUpdate(gettingDepartment).orElse(new Department());
		
		assertThat(updatedDepartment.getName(), not(equalTo("Department 1")));
		
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
