package io.github.alicankustemur.todoapp.service.impl;

import io.github.alicankustemur.todoapp.domain.Meeting;
import io.github.alicankustemur.todoapp.repository.MeetingRepository;
import io.github.alicankustemur.todoapp.service.MeetingService;
import io.github.alicankustemur.todoapp.service.base.AbstractBaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MeetingServiceImpl extends AbstractBaseServiceImpl<Meeting, Long> implements MeetingService {
	
	public MeetingServiceImpl(MeetingRepository repository) {
		super(repository);
	}

}
