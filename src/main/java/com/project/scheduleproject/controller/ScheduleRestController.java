package com.project.scheduleproject.controller;


import com.project.scheduleproject.dto.ScheduleRequestDto;
import com.project.scheduleproject.dto.ScheduleResponseDto;
import com.project.scheduleproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(dto));
    }

    // 일정 조회
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> selectSchedule(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.selectSchedule(id));
    }

    // 모든 일정 조회
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleResponseDto>> selectAllSchedules(@RequestParam (required = false) String userName,
                                                                        @RequestParam(required = false) LocalDate updatedDate){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.selectAllSchedule(userName, updatedDate));
    }

    // 일정 수정
    @PatchMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(id,dto));
    }

    // 일정 삭제
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id,
                                                 @RequestParam String password){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.deleteSchedule(id,password));
    }



}
