package com.nn.nerdnest.member.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {
    // 회원가입시 사용자 입력 데이터

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotNull(message = "직업을 입력해주세요.")
    private Long jobId;

    @NotNull(message = "연차를 입력해주세요.")
    private int level;

    @AssertTrue(message = "동의를 클릭해주세요.")
    private boolean agree;
}
