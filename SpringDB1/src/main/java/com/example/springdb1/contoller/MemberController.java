package com.example.springdb1.contoller;

import com.example.springdb1.domain.Member;
import com.example.springdb1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/member/{id}")
    public Member findMember(@PathVariable long id) {
        return service.getMember(id);
    }

    @PostMapping("/member")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return new ResponseEntity<>(service.saveMember(member), HttpStatus.CREATED);
    }

    @DeleteMapping("/member/{id}")
    public Member deleteMember(@PathVariable long id) {
        return service.deleteMember(id);
    }

    @GetMapping("/member")
    public List<Member> findMembers() {
        return service.getMembers();
    }
}
