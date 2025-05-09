package com.project.scheduleproject.dto;

import com.project.scheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ScheduleDTO {

    private Long scheduleId;
    private Long memberId;
    private String pw;
    private String userName;
    private String title;
    private String contents;
    private String createdDate ;
    private String updatedDate ;


    public ScheduleDTO(Schedule schedule){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.scheduleId = schedule.getScheduleId();
        this.memberId = schedule.getMemberId();
        this.pw = schedule.getPw();
        this.userName = schedule.getUserName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();

        // String 타입으로 변경 (yyyy-MM-dd)
        this.createdDate = schedule.getCreatedDate().format(formatter);
        this.updatedDate = schedule.getUpdatedDate().format(formatter);
    }



    public Schedule toEntity(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime createdDateTime;
        LocalDateTime updatedDateTime;

        // LocalDateTime 타입으로 변경 (yyyy-MM-dd : hh-MM-ss)
        if(this.createdDate ==null){
            createdDateTime = LocalDateTime.now();
        }else{
            createdDateTime = LocalDateTime.parse(this.createdDate,formatter);
        }



        if(this.updatedDate == null){
            updatedDateTime = LocalDateTime.now();
        }else{
            updatedDateTime = LocalDateTime.parse(this.updatedDate,formatter);
        }



        return new Schedule(scheduleId,memberId,pw,userName,title,contents,createdDateTime,updatedDateTime);
    }



}
