package com.project.scheduleproject.dto;

import com.project.scheduleproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MemberDTO {

    private Long id;
    private String userName;
    private String userId;
    private String userPw;
    private String userEmail;
    private String userPhoneNumber;

    public Member toEntity(){
        return new Member(id,userName,userId,userPw,userEmail,userPhoneNumber);
    }
}
