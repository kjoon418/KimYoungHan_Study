package com.example.springdb1.repository;

import com.example.springdb1.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MySqlMemberRepository implements MemberRepository {

    private final DataSource dataSource;
    private final JdbcTemplate template;

    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, name, age) values (?, ?, ?)";
        template.update(sql, member.getMember_id(), member.getName(), member.getAge());

        return member;
    }

    @Override
    public Member findById(long id) {
        String sql = "select * from member where member_id = ?";
        return template.queryForObject(sql, memberMapper(id), id);
    }

    private RowMapper<Member> memberMapper(long id) {
        return (resultSet, rowNumber) -> {
            return Member.builder()
                    .member_id(id)
                    .name(resultSet.getString("name"))
                    .age(resultSet.getInt("age"))
                    .build();
        };
    }

    @Override
    public void delete(long id) {
        String sql = "delete from member where member_id = ?";
        template.update(sql, id);
    }

    @Override
    public Member update(long id, String name, int age) {
        String sql = "update member set name = ?, age = ? where member_id = ?";
        template.update(sql, name, age, id);
        return Member.builder()
                .member_id(id)
                .name(name)
                .age(age)
                .build();
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        return template.query(sql, memberListMapper());
    }

    private RowMapper<Member> memberListMapper() {
        return (resultSet, rowNumber) -> {
            return Member.builder()
                    .member_id(resultSet.getLong("member_id"))
                    .name(resultSet.getString("name"))
                    .age(resultSet.getInt("age"))
                    .build();
        };
    }
}
