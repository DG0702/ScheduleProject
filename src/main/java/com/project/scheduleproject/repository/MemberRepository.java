package com.project.scheduleproject.repository;

import com.project.scheduleproject.dto.MemberResponseDto;
import com.project.scheduleproject.entity.Member;

import java.util.List;

public interface MemberRepository {

    MemberResponseDto addMember(Member member);

    Member findByIdOrElseThrow(Long id);

    List<MemberResponseDto> findAll();

    MemberResponseDto update(Member member);

    String delete(Long id);


}
