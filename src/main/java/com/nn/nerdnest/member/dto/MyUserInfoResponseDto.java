package com.nn.nerdnest.member.dto;

import com.nn.nerdnest.member.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyUserInfoResponseDto {
    // 나의 정보 조회시 보여줄 데이터 항목

    private Long userId;
    private String username;
    private String email;
    private Long jobId;
    private int level;

}
