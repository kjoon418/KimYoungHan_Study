package com.example.springdb1.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Member {

    private long member_id;
    @Setter private String name;
    @Setter private int age;

    public Member() {}

    @Builder
    public Member(long member_id, String name, int age) {
        this.member_id = member_id;
        this.name = name;
        this.age = age;
    }
}
