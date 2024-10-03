package com.example.springdb1.repository;

import com.example.springdb1.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MySqlMemberRepositoryTest {

    MySqlMemberRepository repository;

    Member member1 = new Member(1, "기므준", 24);
    Member member2 = new Member(2, "왓슨", 30);
    Member member3 = new Member(3, "홈즈", 31);


    @BeforeEach
    void init() {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/hello", "root", "104407");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        repository = new MySqlMemberRepository(dataSource, template);
    }

    @AfterEach
    void clear() {
        repository.delete(1);
        repository.delete(2);
        repository.delete(3);
    }

    @Test
    void save() {
        repository.save(member1);
        Member findMember = repository.findById(1);
        assertThat(findMember.getName()).isEqualTo("기므준");
    }

    @Test
    void delete() {
        repository.save(member1);
        repository.delete(1);
    }

    @Test
    void findById() {
        Member savedMember = repository.save(member1);
        Member findMember = repository.findById(1);

        assertThat(savedMember.getAge()).isEqualTo(findMember.getAge());
    }

    @Test
    void update() {
        Member savedMember = repository.save(member1);
        repository.update(1, "hello", 999);
        Member updatedMember = repository.findById(1);

        assertThat(updatedMember.getName()).isEqualTo("hello");
    }

    @Configuration
    static class TestConfig {

        @Autowired
        DataSource dataSource;

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Bean
        MySqlMemberRepository mySqlMemberRepository() {
            return new MySqlMemberRepository(dataSource, jdbcTemplate);
        }
    }

}