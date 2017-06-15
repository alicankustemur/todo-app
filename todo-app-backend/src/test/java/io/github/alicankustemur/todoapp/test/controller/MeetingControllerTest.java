package io.github.alicankustemur.todoapp.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.alicankustemur.todoapp.controller.MeetingController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.InitializeService;
import io.github.alicankustemur.todoapp.service.MeetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MeetingController.class)
public class MeetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InitializeService initializeService;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private MeetingService service;

    private static final String APPLICATION_URL = "http://localhost:3001/meeting";

    // Feature : Meeting Operations
    // Scenario : User want to add a meeting
    @Test
    public void givenMeetingInformationsAndOneDepartmentWhenSendPostRequestWithMeetingThenAddNewMeeting() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post(APPLICATION_URL + "/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(getMeeting())))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MeetingController.class))
                .andExpect(handler().methodName("add"));

    }

    // Feature : Meeting Operations
    // Scenario : User want to show meeting list
    @Test
    public void givenMeetingsWhenGoToListUrlThenGetMeetingList() throws Exception {
        List<Meeting> list = new ArrayList<Meeting>();
        list.add(new Meeting());
        list.add(new Meeting());
        list.add(new Meeting());

        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get(APPLICATION_URL + "/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MeetingController.class))
                .andExpect(handler().methodName("list"));

    }

    // Feature : Meeting Operations
    // Scenario : User want to delete a meeting
    @Test
    public void givenMeetingIdWhenSendDeleteRequestWithIdThenDeleteThisMeeting() throws Exception {

        when(service.get(1L)).thenReturn(getMeeting());

        mockMvc.perform(delete(APPLICATION_URL + "/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MeetingController.class))
                .andExpect(handler().methodName("delete"));
    }

    // Feature : Department Operations
    // Scenario : User want to update a department
    @Test
    public void givenMeetingIdAndMeetingWhenSendPutRequestWithMeetingIdAndDepartmentThenUpdateMeetingWhoseIdIsThisMeetingId() throws Exception {

        //when(employeeService.get(1L)).thenReturn(getDepartment().getEmployee());
        when(service.get(1L)).thenReturn(getMeeting());

        Meeting updatedMeeting = getMeeting();
        updatedMeeting.setName("Goblins");
        updatedMeeting.setRecordUpdateTime(new Date());

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put(APPLICATION_URL + "/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(updatedMeeting)))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MeetingController.class))
                .andExpect(handler().methodName("update"));

    }

    public Meeting getMeeting() {
        Meeting meeting = new Meeting();
        meeting.setId(999L);
        meeting.setName("TestMeeting");
        meeting.setDescription("Description");
        meeting.setDepartment(new Department());

        return meeting;
    }


}