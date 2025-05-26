package com.nn.nerdnest.member.dto;

import com.nn.nerdnest.member.Member;
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
    private Long jobId;
    private int level;
    private boolean agree;
}
