package com.project.scheduleproject.dto;

import com.project.scheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ScheduleResponseDto {
    private Long scheduleId;
    private Long memberId;
    private String userName;
    private String title;
    private String contents;
    private String createdDate ;
    private String updatedDate ;

    //  비밀번호는 출력하지 않음 private String pw;

    public ScheduleResponseDto(Schedule schedule) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.scheduleId = schedule.getScheduleId();
        this.memberId = schedule.getMemberId();
        this.userName = schedule.getUserName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        
        // LocalDateTime -> String 타입으로 변경 (yyyy-MM-dd)
        this.createdDate = schedule.getCreatedDate().format(formatter);
        this.updatedDate = schedule.getUpdatedDate().format(formatter);
    }
}
