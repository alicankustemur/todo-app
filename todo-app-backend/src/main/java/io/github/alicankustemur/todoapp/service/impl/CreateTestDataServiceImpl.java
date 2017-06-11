package io.github.alicankustemur.todoapp.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.service.CreateTestDataService;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import io.github.alicankustemur.todoapp.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CreateTestDataServiceImpl implements CreateTestDataService {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private MeetingService meetingService;

	@Override
	public void create() {
		if(employeeService.getAll().size() == 0){
			Employee employee = new Employee("Frodo","Baggins",10f);
			employee.setRecordIsDeleted(false);
			employee.setRecordCreateTime(new Date());
			
			employeeService.saveOrUpdate(employee);
			
			employee = new Employee("Bilbo","Baggins",15f);
			employee.setRecordIsDeleted(false);
			employee.setRecordCreateTime(new Date());
			
			employeeService.saveOrUpdate(employee);
			
			employee = new Employee("Gandalf","the Grey",100f);
			employee.setRecordIsDeleted(false);
			employee.setRecordCreateTime(new Date());
			
			employeeService.saveOrUpdate(employee);
		}
		
		if(departmentService.getAll().size() == 0){
			Department department  = new Department();
			department.setName("Baggins");
			department.setDescription("The Baggins clan traced their origin to the first recorded Baggins, one Balbo Baggins, who was born in or near Hobbiton in S.R. 1167.");
			Employee employee = employeeService.get(1L);
			department.setEmployee(employee);
			department.setRecordIsDeleted(false);
			department.setRecordCreateTime(new Date());
			
			departmentService.saveOrUpdate(department);
		}
		
		if(meetingService.getAll().size() == 0){
			Meeting meeting = new Meeting();
			meeting.setName("Meeting-1");
			meeting.setDescription("The novel The Lord of the Rings includes as major characters the hobbits "
					+ "Frodo Baggins, Samwise Gamgee, Peregrin Took, and Meriadoc Brandybuck, as well as several other minor hobbit characters.");
			Department department = departmentService.get(1L);
			meeting.setDepartment(department);
			meeting.setRecordIsDeleted(false);
			meeting.setRecordCreateTime(new Date());
			
			meetingService.saveOrUpdate(meeting);
		}
		
		
	}
	
	
	
}
