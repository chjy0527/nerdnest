package com.nn.nerdnest.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyUserInfoResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String job;
    private int level;

}
