package com.nn.nerdnest.member.dto;

import com.nn.nerdnest.member.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyUserInfoResponseDto {
    // 나의 정보 조회시 보여줄 데이터 항목

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "사용자 로그인 아이디", example = "hnoggil05")
    private String username;

    @Schema(description = "사용자 이메일", example = "honggil@naver.com")
    private String email;

    @Schema(description = "직업 ID", example = "1")
    private Long jobId;

    @Schema(description = "연차", example = "3")
    private int level;

}
