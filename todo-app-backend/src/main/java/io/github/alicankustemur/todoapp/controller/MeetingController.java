package io.github.alicankustemur.todoapp.controller;

import java.util.Date;
import java.util.List;

import io.github.alicankustemur.todoapp.controller.base.BaseController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.service.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingController implements BaseController<Meeting> {
	
	@Autowired
	private MeetingService service;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/list")
	@ResponseBody
	@Override
	public List<Meeting> list() {
		return service.getAll();
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public void delete(@PathVariable("id") Long id){
		
		Meeting meeting = service.get(id);
		meeting.setRecordIsDeleted(true);
		meeting.setRecordUpdateTime(new Date());
		
		service.saveOrUpdate(meeting);
	}

	@PostMapping("/add")
	@Override
	public Meeting add(@RequestBody Meeting meeting) {
		meeting.setRecordIsDeleted(false);
		meeting.setRecordCreateTime(new Date());
		return service.saveOrUpdate(meeting).orElse(null);
	}


	@PutMapping("/update/{id}")
	@Override
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Meeting meeting){

		Meeting willBeUpdatedMeeting = service.get(id);

		willBeUpdatedMeeting.setName(meeting.getName());
		willBeUpdatedMeeting.setDescription(meeting.getDescription());
		willBeUpdatedMeeting.setDepartment(meeting.getDepartment());
		willBeUpdatedMeeting.setRecordIsDeleted(false);
		willBeUpdatedMeeting.setRecordUpdateTime(new Date());

		service.saveOrUpdate(willBeUpdatedMeeting);
		
		return ResponseEntity.ok("");
	}
	

}
