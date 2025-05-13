package com.project.scheduleproject.repository;

import com.project.scheduleproject.dto.MemberResponseDto;
import com.project.scheduleproject.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcMemberRepository implements MemberRepository{

    // 필드
    private final JdbcTemplate jdbcTemplate;

    // 생성자
    public JdbcMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 기능
    @Override
    public MemberResponseDto addMember(Member member){

        // INSERT 쿼리 실행
        String sql = "INSERT INTO member (user_name, user_id, user_pw, user_email, user_phone_number) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, member.getUserName(), member.getUserId(), member.getUserPw(), member.getUserEmail(), member.getUserPhoneNumber());

        // 마지막으로 삽인된 ID 가져오기
        String sqlId = "SELECT LAST_INSERT_ID()";
        Long generatedId = jdbcTemplate.queryForObject(sqlId,Long.class);

        member.setId(generatedId);

        return new MemberResponseDto(member);
    }

    @Override
    public Member findByIdOrElseThrow(Long id){

        // SELECT 조회
        String sql = "SELECT * FROM MEMBER WHERE id = ?";
        List<Member> member =
                jdbcTemplate.query (sql,
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Member.class));

        // NULL 처리
        return member.stream()
                .findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist"));
    }

    @Override
    public List<MemberResponseDto> findAll(){

        // 모든 Member 조회
        String sql = "SELECT * FROM MEMBER";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MemberResponseDto.class));
    }

    @Transactional
    @Override
    public MemberResponseDto update(Member member){

        List<Object> params = new ArrayList<>();

        // UPDATE 실행
        String sql = "UPDATE member SET ";

        if(member.getUserName()!= null){
            sql += "user_name = ?, ";
            params.add(member.getUserName());
        }
        if(member.getUserId() != null){
            sql += "user_id = ?, ";
            params.add(member.getUserId());
        }
        if(member.getUserPw()!= null){
            sql += "user_pw = ?, ";
            params.add(member.getUserPw());
        }
        if(member.getUserEmail() != null){
            sql += "user_email = ?, ";
            params.add(member.getUserEmail());
        }
        if(member.getUserPhoneNumber() != null){
            sql += "user_phone_number = ?, ";
            params.add(member.getUserPhoneNumber());
        }

        // 마지막 쉼표제거
        if(sql.endsWith(", ")){
            sql = sql.substring(0, sql.length() -2);
        }

        sql += " WHERE id = ?";
        params.add(member.getId());

        jdbcTemplate.update(sql,params.toArray());

        String selectSql = "SELECT * FROM member WHERE id = ?";
        Member updatedMember = jdbcTemplate.queryForObject(selectSql, new Object[]{member.getId()} ,new BeanPropertyRowMapper<>(Member.class));

        if(updatedMember == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist");
        }

        return new MemberResponseDto(updatedMember);
    }

    @Override
    public String delete(Long id){
        // DELETE 실행
        String sql = "DELETE FROM MEMBER WHERE id = ?";

        // 삭제된 행의 수
        int removeRow = jdbcTemplate.update(sql, id);

        if(removeRow == 0){
            return "삭제할 회원이 존재하지 않습니다.";
        }
        return "삭제가 완료되었습니다.";
    }
}
