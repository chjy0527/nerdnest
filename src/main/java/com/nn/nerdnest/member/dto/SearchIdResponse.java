package com.nn.nerdnest.member.dto;

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
    private String email;
    private String message;
    private boolean success;


}
