package io.github.alicankustemur.todoapp.service.impl;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import io.github.alicankustemur.todoapp.service.InitializeService;
import io.github.alicankustemur.todoapp.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Profile("init")
public class InitializeServiceInitProfilesImpl implements InitializeService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MeetingService meetingService;

    @Transactional
    @Override
    public void init() {

        Employee employee = new Employee("Frodo", "Baggins", 10f,100L);
        employee.setRecordIsDeleted(false);
        employee.setRecordCreateTime(new Date());

        employeeService.saveOrUpdate(employee);

        employee = new Employee("Bilbo", "Baggins", 15f,101L);
        employee.setRecordIsDeleted(false);
        employee.setRecordCreateTime(new Date());

        employeeService.saveOrUpdate(employee);

        employee = new Employee("Gandalf", "the Grey", 100f,102L);
        employee.setRecordIsDeleted(false);
        employee.setRecordCreateTime(new Date());

        employeeService.saveOrUpdate(employee);

        Department department = new Department();
        department.setName("Baggins");
        department.setDescription("The Baggins clan traced their origin to the first recorded Baggins, one Balbo Baggins, who was born in or near Hobbiton in S.R. 1167.");
        department.setEmployee(employeeService.get(1L));
        department.setRecordIsDeleted(false);
        department.setRecordCreateTime(new Date());

        departmentService.saveOrUpdate(department);

        Meeting meeting = new Meeting();
        meeting.setName("Meeting-1");
        meeting.setDescription("The novel The Lord of the Rings includes as major characters the hobbits "
                + "Frodo Baggins, Samwise Gamgee, Peregrin Took, and Meriadoc Brandybuck, as well as several other minor hobbit characters.");
        meeting.setDepartment(departmentService.get(1L));
        meeting.setRecordIsDeleted(false);
        meeting.setRecordCreateTime(new Date());

        meetingService.saveOrUpdate(meeting);

        SpringApplication.exit(applicationContext);

        System.out.println("Application is stoped.");

    }


}
