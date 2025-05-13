package com.project.scheduleproject.repository;

import com.project.scheduleproject.dto.ScheduleResponseDto;
import com.project.scheduleproject.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository {

    // 필드
    private final JdbcTemplate jdbcTemplate;

    // 생성자
    public JdbcScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 기능
    @Override
    public ScheduleResponseDto save (Schedule schedule){

        // INSERT 실행 
        // Now() : mysql에서 현재시간을 자동으로 넣는 함수
        String sql = "INSERT INTO schedule " +
                "(member_id, pw, user_name, title, contents,created_date, updated_date) " +
                "VALUES (?,?,?,?,?,Now(),Now())";

        jdbcTemplate.update(sql,schedule.getMemberId(), schedule.getPw(),schedule.getUserName(),schedule.getTitle(),schedule.getContents());


        String sqlId = "SELECT LAST_INSERT_ID()";
        Long generatedId = jdbcTemplate.queryForObject(sqlId,Long.class);

        schedule.setScheduleId(generatedId);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public Schedule findByIdOrElseThrow (Long id){

        // SELECT 조회
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";

        List<Schedule> schedule =
                jdbcTemplate.query(sql,
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Schedule.class));

        // NULL 처리
        return  schedule.stream()
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist"));
    }

    @Override
    public List<ScheduleResponseDto> findAll (){

        // 모든 Schedule 조회
        String sql = "SELECT * FROM schedule ORDER BY updated_date DESC";
        List<Schedule> schedules =jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Schedule.class));

        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ScheduleResponseDto update (Schedule schedule){

        // 비밀번호 검증
        if(!validPw(schedule)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        List<Object> params = new ArrayList<>();

        // UPDATE 실행
        String sql = "UPDATE schedule SET ";


        if (schedule.getTitle() != null) {
            sql += "title = ?, ";
            params.add(schedule.getTitle());
        }
        if (schedule.getContents() != null) {
            sql += "contents = ?, ";
            params.add(schedule.getContents());
        }

        // 시간 수정
        sql += "updated_date = now() ";


        sql += " WHERE schedule_id = ?";
        params.add(schedule.getScheduleId());


        jdbcTemplate.update(sql, params.toArray());

        String selectSql = "SELECT * FROM schedule WHERE schedule_id = ?";

        Schedule updateSchedule = jdbcTemplate.queryForObject(selectSql, new Object[]{schedule.getScheduleId()}, new BeanPropertyRowMapper<>(Schedule.class));

        
        // NULL 처리
        if(updateSchedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist");
        }

        return new ScheduleResponseDto(updateSchedule);
    }


    @Override
    public String delete (Long id, Schedule schedule){

        if(!validPw(schedule)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // DELETE 실행
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";

        int romoveRow = jdbcTemplate.update(sql,id);

        if(romoveRow == 0){
            return "삭제할 일정이 없습니다";
        }

        return "일정이 삭제 되었습니다";
    }


    public boolean validPw(Schedule schedule){
        String sql = "SELECT pw FROM schedule WHERE schedule_id =?";
        String pw = jdbcTemplate.queryForObject(sql,new Object [] {schedule.getScheduleId()},String.class);

        return schedule.getPw().equals(pw);
    }
}
