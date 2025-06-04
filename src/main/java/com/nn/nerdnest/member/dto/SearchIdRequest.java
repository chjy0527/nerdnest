package com.nn.nerdnest.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchIdRequest {
    // 사용자가 아이디를 찾을 때 입력할 데이터

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}
