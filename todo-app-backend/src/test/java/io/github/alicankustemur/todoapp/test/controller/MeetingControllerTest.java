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
import io.github.alicankustemur.todoapp.controller.MeetingController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.MeetingService;
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
public class MeetingControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private DepartmentService departmentService;
	
	@Mock
	private MeetingService service;

	@InjectMocks
	@Autowired
	private MeetingController controller;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	
	@Test
	public void testGetAllMeetings() throws Exception {
		List<Meeting> list = new ArrayList<Meeting>();
		list.add(new Meeting());
		list.add(new Meeting());
		list.add(new Meeting());
		list.add(new Meeting());
		
		when(service.getAll()).thenReturn(list);
		
		mockMvc.perform(get("/meeting/meetings")
		   .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(jsonPath("$.*", hasSize(4)))
	       .andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteMeeting() throws Exception{
		Meeting meeting = new Meeting();
		meeting.setId(1L);
		meeting.setName("Meeting 1");
		
		when(service.get(1L)).thenReturn(meeting);
		
		mockMvc.perform(get("/meeting/delete/{id}",1L))
						.andExpect(status().is3xxRedirection())
				        .andExpect(view().name("redirect:/meeting"))
				        .andExpect(handler().handlerType(MeetingController.class))
				        .andExpect(handler().methodName("deleteMeeting"))
				        .andReturn();
	}
	
	@Test
	public void testAddMeeting() throws Exception{
		Department department = new Department();
		department.setId(1L);
		department.setName("Department 1");
		
		when(departmentService.get(1L)).thenReturn(department);
		
		mockMvc.perform(post("/meeting/add")
				.param("name", "Meeting 1")
				.param("description", "Lorem ipsum dolor sit amet")
				.param("department", "1"))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/meeting"))
		        .andExpect(handler().handlerType(MeetingController.class))
		        .andExpect(handler().methodName("addMeeting"))
		        .andReturn();
		
	}
	
	
	@Test
	public void testUpdateMeeting() throws Exception{
		Meeting meeting = new Meeting();
		meeting.setId(1L);
		meeting.setName("Meeting 1");
		
		Department department = new Department();
		department.setId(1L);
		
		when(service.get(1L)).thenReturn(meeting);
		when(departmentService.get(1L)).thenReturn(department);
		
		mockMvc.perform(post("/meeting/update")
				.param("id", "1")
				.param("name", "Meeting 1")
				.param("description", "Lorem ipsum dolor sit amet")
				.param("department", "1"))
				.andExpect(status().is3xxRedirection())
		        .andExpect(view().name("redirect:/meeting"))
		        .andExpect(handler().handlerType(MeetingController.class))
		        .andExpect(handler().methodName("updateMeeting"))
		        .andReturn();
		
	}
	
	
}