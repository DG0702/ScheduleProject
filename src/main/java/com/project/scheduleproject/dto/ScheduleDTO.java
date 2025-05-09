package com.project.scheduleproject.dto;

import com.project.scheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;



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
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Schedule toEntity(){
        return new Schedule(scheduleId,memberId,pw,userName,title,contents,createdDate,updatedDate);
    }



}
