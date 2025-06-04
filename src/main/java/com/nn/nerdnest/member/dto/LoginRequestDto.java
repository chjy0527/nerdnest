package com.nn.nerdnest.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    // 사용자가 로그인 시 입력할 데이터

    @Schema(description = "유저이름", example = "jin05")
    private String username;

    @Schema(description = "비밀번호", example = "wls12")
    private String password;
}
