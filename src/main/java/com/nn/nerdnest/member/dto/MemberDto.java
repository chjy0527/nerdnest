package com.nn.nerdnest.member.dto;

import com.nn.nerdnest.member.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private String name;
    private String email;
    private String username;
    private String job;
    private int level;

    public MemberDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.job = member.getJob();
        this.level = member.getLevel();
    }
}
