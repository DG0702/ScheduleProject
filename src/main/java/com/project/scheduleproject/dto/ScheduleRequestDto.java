package com.project.scheduleproject.dto;

import com.project.scheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ScheduleRequestDto {

    private Long memberId;
    private String pw;
    private String userName;
    private String title;
    private String contents;
    private String createdDate ;
    private String updatedDate ;


    public ScheduleRequestDto(Schedule schedule){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.memberId = schedule.getMemberId();
        this.pw = schedule.getPw();
        this.userName = schedule.getUserName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();

        // LocalDateTime -> String 타입으로 변경 (yyyy-MM-dd)
        this.createdDate = schedule.getCreatedDate().format(formatter);
        this.updatedDate = schedule.getUpdatedDate().format(formatter);
    }



    public Schedule toEntity(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime createdDateTime;
        LocalDateTime updatedDateTime;

        // String -> LocalDateTime 타입으로 변경 (yyyy-MM-dd : hh-MM-ss)
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


        return new Schedule(memberId,pw,userName,title,contents,createdDateTime,updatedDateTime);
    }



}
