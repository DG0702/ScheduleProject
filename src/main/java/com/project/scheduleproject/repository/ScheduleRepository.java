package com.project.scheduleproject.repository;

import com.project.scheduleproject.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    Schedule save(Schedule schedule);

    Schedule findById(Long id);

    List<Schedule> findAll();

    Schedule update(Schedule schedule);

    String delete(Long id);

}


