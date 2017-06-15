package io.github.alicankustemur.todoapp.controller;

import java.util.Date;
import java.util.List;

import io.github.alicankustemur.todoapp.controller.base.BaseController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
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


    @PostMapping("/add")
    @Override
    public ResponseEntity<Meeting> add(@RequestBody Meeting meeting) {
        meeting.setRecordCreateTime(new Date());
        meeting.setRecordIsDeleted(false);

        return ResponseEntity.ok(service.saveOrUpdate(meeting));
    }

    @GetMapping("/list")
    @ResponseBody
    @Override
    public List<Meeting> list() {
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<Meeting> delete(@PathVariable("id") Long id) {
        Meeting deletedMeeting = service.get(id);
        deletedMeeting.setRecordIsDeleted(true);
        deletedMeeting.setRecordUpdateTime(new Date());

        return ResponseEntity.ok(service.saveOrUpdate(deletedMeeting));
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<Meeting> update(@PathVariable("id") Long id,@RequestBody Meeting meeting) {
        Meeting willBeUpdatedMeeting = service.get(id);
        willBeUpdatedMeeting.setName(meeting.getName());
        willBeUpdatedMeeting.setDescription(meeting.getDescription());
        willBeUpdatedMeeting.setDepartment(meeting.getDepartment());
        willBeUpdatedMeeting.setRecordUpdateTime(new Date());

        return ResponseEntity.ok(service.saveOrUpdate(willBeUpdatedMeeting));
    }
}
