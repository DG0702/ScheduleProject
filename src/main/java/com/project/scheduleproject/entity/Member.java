package com.project.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Member {

    private Long id;
    private String userName;
    private String userPw;
    private String userEmail;
    private String userPhoneNumber;

    public Member(String userName,  String userPw, String userEmail, String userPhoneNumber) {
        this.userName = userName;
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
    }
}
