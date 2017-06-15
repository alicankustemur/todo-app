package io.github.alicankustemur.todoapp.test.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.repository.MeetingRepository;
import io.github.alicankustemur.todoapp.service.MeetingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingServiceTest {

	@Mock
	private MeetingRepository repository;

	@InjectMocks
	@Autowired
	private MeetingService service;

	@Test
	public void testAddNewMeeting() throws Exception {
		Meeting meeting = getMeeting();
		when(repository.findOne(meeting.getId())).thenReturn(null);
		when(repository.save(meeting)).thenReturn(meeting);

		Meeting gettingMeeting = service.saveOrUpdate(meeting);

		assertThat(gettingMeeting.getId(), is(equalTo(meeting.getId())));
	}
	
	@Test	
	public void testRemoveMeeting() throws Exception{
		Meeting meeting = getMeeting();
		
		when(repository.findOne(meeting.getId())).thenReturn(null);
		when(repository.save(meeting)).thenReturn(meeting);

		Meeting gettingMeeting = service.saveOrUpdate(meeting);
		gettingMeeting.setRecordIsDeleted(true);
		gettingMeeting.setRecordUpdateTime(new Date());
		service.saveOrUpdate(gettingMeeting);
		
		Meeting removedMeeting = service.get(gettingMeeting.getId());

		assertThat(removedMeeting, nullValue());
		
	}
	
	@Test	
	public void testUpdateMeeting() throws Exception{
		Meeting meeting = getMeeting();
		
		when(repository.findOne(meeting.getId())).thenReturn(null);
		when(repository.save(meeting)).thenReturn(meeting);

		Meeting gettingMeeting = service.saveOrUpdate(meeting);
		gettingMeeting.setName("Meeting 2");;
		gettingMeeting.setRecordUpdateTime(new Date());
		Meeting updatedMeeting = service.saveOrUpdate(gettingMeeting);
		
		assertThat(updatedMeeting.getName(), not(equalTo("Meeting 1")));
		
	}
	

	public Meeting getMeeting() {
		Meeting meeting = new Meeting();
		meeting.setId(1L);
		meeting.setName("Meeting 1");
		meeting.setDescription("Lorem ipsum dolor sit amet.");
		meeting.setRecordIsDeleted(false);
		meeting.setRecordCreateTime(new Date());
		
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
		
		meeting.setDepartment(department);
		
		return meeting;
	}

}
