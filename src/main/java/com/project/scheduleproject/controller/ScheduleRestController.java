package com.project.scheduleproject.controller;


import com.project.scheduleproject.dto.ScheduleDTO;
import com.project.scheduleproject.entity.Schedule;
import com.project.scheduleproject.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleRestController {

    // 필드
    private final ScheduleService scheduleService;

    // 생성자
    public ScheduleRestController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성
    @PostMapping("/schedule")
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDTO scheduleDTO){

        Schedule schedule = scheduleDTO.toEntity();

        Schedule savedSchedule = scheduleService.createSchedule(schedule);

        return ResponseEntity.ok(savedSchedule);
    }

    // 일정 조회
    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> selectSchedule(@PathVariable Long id){
        Schedule schedule = scheduleService.selectSchedule(id);
        return ResponseEntity.ok(schedule);
    }

    // 모든 일정 조회
    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> selectAllSchedules(){
        List<Schedule> schedules = scheduleService.selectAllSchedule();
        return ResponseEntity.ok(schedules);
    }
    
    // 일정 수정
    @PatchMapping("/schedule/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO){
        Schedule schedule = scheduleDTO.toEntity();

        schedule.setScheduleId(id);

        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);

        return ResponseEntity.ok(updatedSchedule);
    }

    // 일정 삭제
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id){
        String result =  scheduleService.deleteSchedule(id);
        return ResponseEntity.ok(result);
    }
    
    

}
