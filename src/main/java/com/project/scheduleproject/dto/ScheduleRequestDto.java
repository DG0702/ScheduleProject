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

public class ScheduleRequestDto {

    private Long memberId;
    private String pw;
    private String userName;
    private String title;
    private String contents;
    private LocalDateTime createdDate ;
    private LocalDateTime updatedDate ;
    
    

    public Schedule toEntity(){
        LocalDateTime createdDateTime;
        LocalDateTime updatedDateTime;

        // 생성일이 null 일 경우
        if(this.createdDate ==null){
            createdDateTime = LocalDateTime.now();
        }
        else{
            createdDateTime = this.createdDate;
        }

        // 수정일이 null 일 경우
        if(this.updatedDate == null){
            updatedDateTime = LocalDateTime.now();
        }
        else{
            updatedDateTime = this.updatedDate;
        }


        return new Schedule(memberId,pw,userName,title,contents,createdDateTime,updatedDateTime);
    }



}
