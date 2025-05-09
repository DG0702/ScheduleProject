package com.project.scheduleproject.repository;

import com.project.scheduleproject.entity.Schedule;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public Schedule save (Schedule schedule){

        // INSERT 실행 
        // Now() : mysql에서 현재시간을 자동으로 넣는 함수
        String sql = "INSERT INTO schedule " +
                "(member_id, pw, user_name, title, contents,created_date, updated_date) " +
                "VALUES (?,?,?,?,?,Now(),Now())";

        jdbcTemplate.update(sql,schedule.getMemberId(), schedule.getPw(),schedule.getUserName(),schedule.getTitle(),schedule.getContents());

        String sqlId = "SELECT LAST_INSERT_ID()";
        Long generatedId = jdbcTemplate.queryForObject(sqlId,Long.class);

        schedule.setScheduleId(generatedId);

        return schedule;
    }

    @Override
    public Schedule findById (Long id){

        // SELECT 조회
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";
        Schedule schedule =
                jdbcTemplate.queryForObject(sql,new Object[]{id}, new BeanPropertyRowMapper<>(Schedule.class));
        return  schedule;
    }

    @Override
    public List<Schedule> findAll (){

        // 모든 Schedule 조회
        String sql = "SELECT * FROM schedule";
        List<Schedule> schedules = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Schedule.class));
        return schedules;
    }

    @Override
    public Schedule update (Schedule schedule){
        List<Object> params = new ArrayList<>();

        // UPDATE 실행
        String sql = "UPDATE schedule SET ";

        if(schedule.getMemberId() != null){
            sql += "member_id = ?, ";
            params.add(schedule.getMemberId());
        }
        if(schedule.getPw() != null){
            sql += "pw = ?, ";
            params.add(schedule.getPw());
        }
        if(schedule.getUserName() != null){
            sql += "user_name = ?, ";
            params.add(schedule.getUserName());
        }
        if(schedule.getTitle() != null){
            sql += "title = ?, ";
            params.add(schedule.getTitle());
        }
        if(schedule.getContents() != null){
            sql += "contents = ?, ";
            params.add(schedule.getContents());
        }

        // 마지막 쉼표 제거
        if(sql.endsWith(", ")){
            sql = sql.substring(0, sql.length() -2);
        }

        sql += " WHERE id = ?";
        params.add(schedule.getScheduleId());

        jdbcTemplate.update(sql,params.toArray());

        String selectSql = "SELECT * FROM schedule WHERE schedule_id = ?";
        Schedule updateSchedule =  jdbcTemplate.queryForObject(selectSql, new Object [] {schedule.getScheduleId()}, new BeanPropertyRowMapper<>(Schedule.class));

        return updateSchedule;
    }

    @Override
    public String delete (Long id){

        // DELETE 실행
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";

        int romoveRow = jdbcTemplate.update(sql,id);

        if(romoveRow == 0){
            return "삭제할 일정이 없습니다";
        }

        return "일정이 삭제 되었습니다";
    }
}
