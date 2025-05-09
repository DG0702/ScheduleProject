package com.project.scheduleproject.dto;

import com.project.scheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.format.DateTimeFormatter;

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
    private DateTimeFormatter createdDate;
    private DateTimeFormatter updatedDate;

    public Schedule toEntity(){
        if(createdDate == null){
            createdDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        if(updatedDate == null){
            updatedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        return new Schedule(scheduleId,memberId,pw,userName,title,contents,createdDate,updatedDate);
    }

}
