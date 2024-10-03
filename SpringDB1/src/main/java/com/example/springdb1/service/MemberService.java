package com.example.springdb1.service;

import com.example.springdb1.domain.Member;
import com.example.springdb1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Member saveMember(Member member) {
        repository.save(member);

        return member;
    }

    @Transactional
    public Member updateMember(long id, String name, int age) {
        repository.update(id, name, age);

        return repository.findById(id);
    }

    @Transactional
    public Member deleteMember(long id) {
        Member member = repository.findById(id);
        repository.delete(id);

        return member;
    }

    public Member getMember(long id) {
        return repository.findById(id);
    }

    public List<Member> getMembers() {
        return repository.findAll();
    }
}
