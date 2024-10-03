package com.example.springdb1.repository;

import com.example.springdb1.domain.Member;

import java.util.List;

public interface MemberRepository {

    public Member save(Member member);

    public Member findById(long id);

    public void delete(long id);

    public Member update(long id, String name, int age);

    public List<Member> findAll();
}
