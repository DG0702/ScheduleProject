package com.project.scheduleproject.repository;

import com.project.scheduleproject.dto.ScheduleResponseDto;
import com.project.scheduleproject.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto save(Schedule schedule);

    Schedule findByIdOrElseThrow(Long id);

    List<ScheduleResponseDto> findAll(LocalDate updatedDate);

    ScheduleResponseDto update(Schedule schedule);

    String delete(Long id, String password);

}


