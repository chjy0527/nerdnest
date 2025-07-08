package com.nn.nerdnest.member;


import com.nn.nerdnest.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import com.nn.nerdnest.exception.BusinessException;
import com.nn.nerdnest.exception.ErrorCode;

@Component
@RequiredArgsConstructor
public class MemberValidator {
    private final MemberRepository memberRepository;

    public void validator(MemberRequestDto memberRequestDto) {

        // email 중복 체크
        Optional<Member> emailCheck = memberRepository.findByEmail(memberRequestDto.getEmail());
        if (emailCheck.isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        // username 중복 체크
        Optional<Member> usernameCheck = memberRepository.findByUsername(memberRequestDto.getUsername());
        if (usernameCheck.isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_USERNAME);
        }
    }

}
