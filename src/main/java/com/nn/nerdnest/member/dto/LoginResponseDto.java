package com.nn.nerdnest.member.dto;

import com.nn.nerdnest.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    // 로그인 시 보여줄 데이터 항목
    @Schema(description = "사용자 로그인 Token", example = "dc9we5gxx4wt2c@")
    private String accessToken;

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

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

    @Schema(description = "개인정보 동의", example = "true/false")
    private boolean agree;
}
