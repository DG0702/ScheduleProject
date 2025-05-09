package com.project.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Schedule {
    private Long scheduleId;
    private Long memberId;
    private String pw;
    private String userName;
    private String title;
    private String contents;
    private DateTimeFormatter createdDate;
    private DateTimeFormatter updatedDate;

}
