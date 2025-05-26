package com.nn.nerdnest.member.dto;

import com.nn.nerdnest.member.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyUserInfoResponseDto {
    private Long userId;
    private String username;
    private String email;
    private Long jobId;
    private int level;

}
