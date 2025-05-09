package com.project.scheduleproject.controller;

import com.project.scheduleproject.dto.MemberDTO;
import com.project.scheduleproject.entity.Member;
import com.project.scheduleproject.repository.JdbcMemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberRestController {

    // 필드
    private final JdbcMemberRepository jdbcMemberRepository;

    // 생성자
    public MemberRestController(JdbcMemberRepository jdbcMemberRepository) {
        this.jdbcMemberRepository = jdbcMemberRepository;
    }


    // 멤버 생성
    @PostMapping ("/member")
    public ResponseEntity<Member> createMember(@RequestBody MemberDTO memberDTO){

         Member newMember = memberDTO.toEntity();

         newMember = jdbcMemberRepository.save(newMember);

         return ResponseEntity.ok(newMember);
    }

    // 멤버 조회
    @GetMapping("/member/{id}")
    public ResponseEntity<Member> selectMember(@PathVariable Long id){
        Member member = jdbcMemberRepository.findById(id);

        return ResponseEntity.ok(member);
    }

    // 모든 멤버 조회
    @GetMapping("/member")
    public ResponseEntity<List<Member>> selectAllMember(){
        List<Member> members = jdbcMemberRepository.findALL();

        return ResponseEntity.ok(members);
    }
    
    // 멤버 수정
    @PatchMapping("/member/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO){
        Member member = memberDTO.toEntity();

        member.setId(id);

        Member updatedMember = jdbcMemberRepository.update(member);

        return ResponseEntity.ok(updatedMember);
    }

    
    // 멤버 삭제
    @DeleteMapping("/member/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id){

        String confirm = jdbcMemberRepository.delete(id);
        return ResponseEntity.ok(confirm);
    }


}
