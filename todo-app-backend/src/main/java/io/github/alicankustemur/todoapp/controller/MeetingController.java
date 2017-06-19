package io.github.alicankustemur.todoapp.controller;

import io.github.alicankustemur.todoapp.controller.base.AbstractBaseController;
import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.service.MeetingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meeting")
public class MeetingController extends AbstractBaseController<Meeting,MeetingService> {


}
