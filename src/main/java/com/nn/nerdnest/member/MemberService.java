package com.nn.nerdnest.member;


import com.nn.nerdnest.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberValidator memberValidator;
    private final JwtUtil jwtUtil;


    // 회원가입
    @Transactional
    public MemberDto saveMember(MemberRequestDto memberRequestDto) {

        // 유효성
        memberValidator.validator(memberRequestDto);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode((memberRequestDto.getPassword()));

        Member member = new Member(
                memberRequestDto.getName(),
                memberRequestDto.getEmail(),
                memberRequestDto.getUsername(),
                encodedPassword,
                memberRequestDto.getJob(),
                memberRequestDto.getLevel(),
                memberRequestDto.isAgree()
        );


        Member savedMember = memberRepository.save(member);
        return new MemberDto(savedMember);
    }

    // 로그인
    public String login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("아이디와 비밀번호를 모두 입력해주세요.");
        }

        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if(memberOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        Member member = memberOptional.get();
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return jwtUtil.generateToken(username);
    }

    // 나의 정보
    public MyUserInfoResponseDto myUserInfo(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다."));

        return new MyUserInfoResponseDto(
                member.getUsername(),
                member.getJob(),
                member.getLevel()
        );
    }

    // 아이디 찾기
    public SearchIdResponse searchIdByEmail (SearchIdRequest searchIdRequest) {
        Optional<Member> memberOptional = memberRepository.findByEmail(searchIdRequest.getEmail());

        if (memberOptional.isEmpty()) {
            return SearchIdResponse.builder()
                    .success(false)
                    .message("해당 이메일로 가입된 회원이 없습니다.")
                    .build();
        }
        Member member = memberOptional.get();

        return SearchIdResponse.builder()
                .email(member.getEmail())
                .success(true)
                .message("아이디 찾기에 성공했습니다.")
                .build();
    }
}
