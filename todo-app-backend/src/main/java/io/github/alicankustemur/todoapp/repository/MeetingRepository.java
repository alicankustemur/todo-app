package io.github.alicankustemur.todoapp.repository;

import io.github.alicankustemur.todoapp.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import io.github.alicankustemur.todoapp.domain.Meeting;

@Repository
public interface MeetingRepository extends BaseRepository<Meeting, Long> {

}
