package com.project.scheduleproject.repository;

import com.project.scheduleproject.entity.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    List<Member> findAll();

    Member update(Member member);

    String delete(Long id);


}
