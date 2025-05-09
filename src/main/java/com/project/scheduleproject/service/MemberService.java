package com.project.scheduleproject.service;

import com.project.scheduleproject.entity.Member;
import com.project.scheduleproject.repository.JdbcMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    // 필드
    private final JdbcMemberRepository jdbcMemberRepository;

    // 생성자
    public MemberService(JdbcMemberRepository jdbcMemberRepository){
        this.jdbcMemberRepository = jdbcMemberRepository;
    }

    // 멤버 생성
    public Member createMember(Member member){
       return jdbcMemberRepository.save(member);
    }
    
    // 멤버 조회
    public Member selectMember(Long id){
        return jdbcMemberRepository.findById(id);
    }

    // 모든 멤버 조회
    public List<Member> selectAllMembers(){
        return jdbcMemberRepository.findAll();
    }
    
    // 멤버 수정
    public Member updateMember(Member member){
        return jdbcMemberRepository.update(member);
    }

    // 멤버 삭제
    public String deleteMember(Long id){
        return jdbcMemberRepository.delete(id);
    }


}
