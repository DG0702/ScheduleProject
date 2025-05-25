package com.project.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Schedule {
    private Long scheduleId;
    private Long memberId;
    private String pw;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Schedule(Long memberId, String pw, String title, String contents, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.memberId = memberId;
        this.pw = pw;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDateTime;
        this.updatedDate = updatedDateTime;
    }
}


