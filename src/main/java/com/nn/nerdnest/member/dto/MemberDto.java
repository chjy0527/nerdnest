package com.nn.nerdnest.member.dto;

import com.nn.nerdnest.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberDto {
    // 회원가입시 보여줄 데이터 항목
    @Schema(description = "사용자 이름", example = "김홍길")
    private String name;

    @Schema(description = "사용자 이메일", example = "honggil@naver.com")
    private String email;

    @Schema(description = "사용자 로그인 아이디", example = "hnoggil05")
    private String username;

    @Schema(description = "직업 ID", example = "1")
    private Long jobId;

    @Schema(description = "연차", example = "3")
    private int level;

    public MemberDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.jobId = member.getJob().getId();
        this.level = member.getLevel();
    }
}
