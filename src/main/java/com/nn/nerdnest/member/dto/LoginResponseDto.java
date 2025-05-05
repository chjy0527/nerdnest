package com.nn.nerdnest.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String accessToken;
    private Long userId;
    private String name;
    private String email;
    private String username;
    private String job;
    private int level;
    private boolean agree;
}
