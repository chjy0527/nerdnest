package com.nn.nerdnest.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SearchIdResponse {
    // 사용자가 로그인 찾은 후 보여줄 데이터 항목

    @Schema(description = "사용자 이메일", example = "honggil@naver.com")
    private String email;

    @Schema(description = "이메일 ", example = "honggil@naver.com")
    private String message;

    @Schema(description = "아이디 찾기 성공 여부", example = "true/false")
    private boolean success;


}
