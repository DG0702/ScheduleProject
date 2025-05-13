package com.project.scheduleproject.service;

import com.project.scheduleproject.dto.ScheduleRequestDto;
import com.project.scheduleproject.dto.ScheduleResponseDto;
import com.project.scheduleproject.entity.Schedule;
import com.project.scheduleproject.repository.JdbcScheduleRepository;
import com.project.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    // 필드
    private final ScheduleRepository scheduleRepository;

    // 생성자
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 생성
    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        Schedule schedule = dto.toEntity();
        return scheduleRepository.save(schedule);
    }
    
    // 일정 조회
    @Override
    public ScheduleResponseDto selectSchedule(Long id){
        Schedule schedule =scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }
    
    // 일정 모두 조회
    @Override
    public List<ScheduleResponseDto> selectAllSchedule(){
        return scheduleRepository.findAll();
    }
    
    // 일정 수정
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto){
        Schedule schedule = dto.toEntity();

        schedule.setScheduleId(id);

        return scheduleRepository.update(schedule);
    }
    
    // 일정 삭제
    @Override
    public String deleteSchedule(Long id, ScheduleRequestDto dto){
        Schedule schedule = dto.toEntity();

        schedule.setScheduleId(id);

        return scheduleRepository.delete(id,schedule);
    }
}
