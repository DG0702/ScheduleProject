package com.project.scheduleproject.service;

import com.project.scheduleproject.entity.Schedule;
import com.project.scheduleproject.repository.JdbcScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    // 필드
    private final JdbcScheduleRepository jdbcScheduleRepository;

    // 생성자
    public ScheduleService(JdbcScheduleRepository jdbcScheduleRepository) {
        this.jdbcScheduleRepository = jdbcScheduleRepository;
    }

    // 일정 생성
    public Schedule createSchedule(Schedule schedule) {
        return jdbcScheduleRepository.save(schedule);
    }
    
    // 일정 조회
    public Schedule selectSchedule(Long id){
        return jdbcScheduleRepository.findById(id);
    }
    
    // 일정 모두 조회
    public List<Schedule> selectAllSchedule(){
        return jdbcScheduleRepository.findAll();
    }
    
    // 일정 수정
    public Schedule updateSchedule(Schedule schedule){
        return jdbcScheduleRepository.update(schedule);
    }
    
    // 일정 삭제
    public String deleteSchedule(Long id, Schedule schedule){
        return jdbcScheduleRepository.delete(id,schedule );
    }
}
