package com.project.scheduleproject.service;

import com.project.scheduleproject.dto.MemberRequestDto;
import com.project.scheduleproject.dto.MemberResponseDto;
import java.util.List;

public interface MemberService {
    MemberResponseDto addMember(MemberRequestDto dto);
    MemberResponseDto selectMember(Long id);
    List<MemberResponseDto> selectAllMembers();
    MemberResponseDto updateMember(Long id, MemberRequestDto dto);
    String deleteMember(Long id);

}
