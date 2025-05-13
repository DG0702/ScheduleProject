package com.project.scheduleproject.service;

import com.project.scheduleproject.dto.MemberRequestDto;
import com.project.scheduleproject.dto.MemberResponseDto;
import com.project.scheduleproject.entity.Member;
import com.project.scheduleproject.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    // 필드
    private final MemberRepository MemberRepository;

    // 생성자
    public MemberServiceImpl(MemberRepository MemberRepository){
        this.MemberRepository = MemberRepository;
    }

    // 멤버 생성
    public MemberResponseDto addMember(MemberRequestDto dto){

        Member member = dto.toEntity();

        return MemberRepository.addMember(member);
    }
    
    // 멤버 조회
    public MemberResponseDto selectMember(Long id){
        Member member = MemberRepository.findByIdOrElseThrow(id);
        return new MemberResponseDto(member);
    }

    // 모든 멤버 조회
    public List<MemberResponseDto> selectAllMembers(){
        return MemberRepository.findAll();
    }

    // 멤버 수정
    public MemberResponseDto updateMember(Long id,MemberRequestDto dto){

        if(dto == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"dto cannot be null");
        }

        Member member = dto.toEntity();

        member.setId(id);

        return MemberRepository.update(member);
    }

    // 멤버 삭제
    public String deleteMember(Long id){
        return MemberRepository.delete(id);
    }


}
