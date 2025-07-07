package com.nn.nerdnest.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {
    // 회원가입시 사용자 입력 데이터

    @Schema(description = "사용자 이름", example = "김홍길")
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @Schema(description = "사용자 이메일", example = "honggil@naver.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Schema(description = "사용자 로그인 아이디", example = "hnoggil05")
    @NotBlank(message = "로그인 아이디를 입력해주세요.")
    private String username;

    @Schema(description = "비밀번호", example = "sdfj@kZ5@14ds")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Schema(description = "직업 ID", example = "1")
    @NotNull(message = "직업을 입력해주세요.")
    private Long jobId;

    @Schema(description = "연차", example = "3")
    @NotNull(message = "연차를 입력해주세요.")
    private int level;

    @Schema(description = "개인정보 동의", example = "true/false")
    @AssertTrue(message = "동의를 클릭해주세요.")
    private boolean agree;
}
