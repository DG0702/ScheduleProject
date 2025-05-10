package com.project.scheduleproject.controller;


import com.project.scheduleproject.dto.ScheduleDTO;
import com.project.scheduleproject.entity.Schedule;
import com.project.scheduleproject.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ScheduleDTO> selectSchedule(@PathVariable Long id){
        Schedule schedule = scheduleService.selectSchedule(id);

        ScheduleDTO scheduleDTO = new ScheduleDTO(schedule);

        return ResponseEntity.ok(scheduleDTO);
    }

    // 모든 일정 조회
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleDTO>> selectAllSchedules(){
        List<Schedule> schedules = scheduleService.selectAllSchedule();

//        List<ScheduleDTO> scheduleDTOList = schedules.stream()
//                .map(schedule -> new ScheduleDTO(schedule))
//                .map(ScheduleDTO::new)
//                .collect(Collectors.toList());



        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = new ScheduleDTO(schedule);
            scheduleDTOList.add(scheduleDTO);
        }

        return ResponseEntity.ok(scheduleDTOList);
    }
    
    // 일정 수정
    @PatchMapping("/schedule/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO){
        Schedule schedule = scheduleDTO.toEntity();

        schedule.setScheduleId(id);

        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);

        ScheduleDTO scheduleDTo = new ScheduleDTO(updatedSchedule);

        return ResponseEntity.ok(scheduleDTo);
    }

    // 일정 삭제
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO){
        Schedule schedule = scheduleDTO.toEntity();

        schedule.setScheduleId(id);

        String result =  scheduleService.deleteSchedule(id, schedule);
        return ResponseEntity.ok(result);
    }
    
    

}
