package com.project.scheduleproject.service;

import com.project.scheduleproject.dto.ScheduleRequestDto;
import com.project.scheduleproject.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    ScheduleResponseDto selectSchedule(Long id);
    List<ScheduleResponseDto> selectAllSchedule();
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);
    String deleteSchedule(Long id, ScheduleRequestDto dto);



}
