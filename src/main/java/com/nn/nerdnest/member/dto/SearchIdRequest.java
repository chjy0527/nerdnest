package com.nn.nerdnest.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchIdRequest {
    // 사용자가 아이디를 찾을 때 입력할 데이터
    @Schema(description = "사용자 이메일", example = "honggil@naver.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}
