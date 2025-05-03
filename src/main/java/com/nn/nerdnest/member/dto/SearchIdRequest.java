package com.nn.nerdnest.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchIdRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}
