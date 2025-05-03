package com.nn.nerdnest.member;


import com.nn.nerdnest.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberValidator {
    private final MemberRepository memberRepository;

    public void validator(MemberRequestDto memberRequestDto) {

        // email 중복 체크
        Optional<Member> emailCheck = memberRepository.findByEmail(memberRequestDto.getEmail());
        if (emailCheck.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        // username 중복 체크
        Optional<Member> usernameCheck = memberRepository.findByUsername(memberRequestDto.getUsername());
        if (usernameCheck.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름 입니다..");
        }
    }

}
