package com.project.scheduleproject.controller;

import com.project.scheduleproject.dto.MemberDTO;
import com.project.scheduleproject.entity.Member;
import com.project.scheduleproject.service.MemberService;
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
    public ResponseEntity<Member> createMember(@RequestBody MemberDTO memberDTO){

         Member newMember = memberDTO.toEntity();

         newMember = memberService.createMember(newMember);

         return ResponseEntity.ok(newMember);
    }

    // 멤버 조회
    @GetMapping("/member/{id}")
    public ResponseEntity<Member> selectMember(@PathVariable Long id){
        Member member = memberService.selectMember(id);

        return ResponseEntity.ok(member);
    }

    // 모든 멤버 조회
    @GetMapping("/member")
    public ResponseEntity<List<Member>> selectAllMember(){
        List<Member> members = memberService.selectAllMembers();

        return ResponseEntity.ok(members);
    }
    
    // 멤버 수정
    @PatchMapping("/member/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO){
        Member member = memberDTO.toEntity();

        member.setId(id);

        Member updatedMember = memberService.updateMember(member);

        return ResponseEntity.ok(updatedMember);
    }

    
    // 멤버 삭제
    @DeleteMapping("/member/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id){

        String confirm = memberService.deleteMember(id);
        return ResponseEntity.ok(confirm);
    }


}
