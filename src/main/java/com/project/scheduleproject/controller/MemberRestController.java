package com.project.scheduleproject.controller;

import com.project.scheduleproject.dto.MemberRequestDto;
import com.project.scheduleproject.dto.MemberResponseDto;
import com.project.scheduleproject.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberRestController {

    // 필드
    private final MemberService memberService;

    // 생성자
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 멤버 생성
    @PostMapping ("/member")
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.addMember(dto));
    }

    // 멤버 조회
    @GetMapping("/member/{id}")
    public ResponseEntity<MemberResponseDto> selectMember(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.selectMember(id));
    }

    // 모든 멤버 조회
    @GetMapping("/member")
    public ResponseEntity<List<MemberResponseDto>> selectAllMember(){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.selectAllMembers());
    }

    // 멤버 수정
    @PatchMapping("/member/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id, @RequestBody MemberRequestDto dto){
        return new ResponseEntity<>(memberService.updateMember(id,dto),HttpStatus.OK);
    }


    // 멤버 삭제
    @DeleteMapping("/member/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id){
        return new ResponseEntity<>(memberService.deleteMember(id),HttpStatus.OK);
    }


}
