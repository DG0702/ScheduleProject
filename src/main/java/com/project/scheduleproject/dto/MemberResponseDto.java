package com.project.scheduleproject.dto;

import com.project.scheduleproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class MemberResponseDto {
    private Long id;
    private String userName;
    private String userId;
    private String userPw;
    private String userEmail;
    private String userPhoneNumber;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.userName = member.getUserName();
        this.userId = member.getUserId();
        this.userPw = member.getUserPw();
        this.userEmail = member.getUserEmail();
        this.userPhoneNumber = member.getUserPhoneNumber();
    }

}
