package com.project.scheduleproject.service;

import com.project.scheduleproject.dto.ScheduleRequestDto;
import com.project.scheduleproject.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    ScheduleResponseDto selectSchedule(Long id);
    List<ScheduleResponseDto> selectAllSchedule(String userName , LocalDate updatedDate);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);
    String deleteSchedule(Long id, String password);



}
