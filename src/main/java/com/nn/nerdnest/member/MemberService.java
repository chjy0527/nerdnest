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
    private final JobRepository jobRepository;


    // 회원가입(saveMember)
    @Transactional
    public MemberDto saveMember(MemberRequestDto memberRequestDto) {

        // 유효성 검사
        memberValidator.validator(memberRequestDto);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode((memberRequestDto.getPassword()));

        // job id 조회
        Job job = jobRepository.findById(memberRequestDto.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직업입니다."));

        // 사용자 아아디(이름) 중복검사
        if (memberRepository.findByUsername(memberRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }

        // 사용자가 입력한 회원가입 정보
        Member member = new Member(
                memberRequestDto.getName(),
                memberRequestDto.getEmail(),
                memberRequestDto.getUsername(),
                encodedPassword,
                job,
                memberRequestDto.getLevel(),
                memberRequestDto.isAgree()
        );

        // 회원가입 정보 저장
        Member savedMember = memberRepository.save(member);

        return new MemberDto(savedMember);
    }

    // 로그인(login)
    public String login(String username, String password) {
        // 아이디, 비밀번호 입력 체크
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("아이디와 비밀번호를 모두 입력해주세요.");
        }

        // Username 조회
        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        // 아이디 유효성 체크
        if(memberOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        // 비밀번호 유효성 체크
        Member member = memberOptional.get();
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return jwtUtil.generateToken(username);
    }

    // 나의 정보 조회(myUserInfo)
    public MyUserInfoResponseDto myUserInfo(String username) {
        // 사용자 정보 조회
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다."));

        // 사용자 정보 조회 성공시, 아래 데이터 return
        return new MyUserInfoResponseDto(
                member.getId(),
                member.getUsername(),
                member.getEmail(),
                member.getJob().getId(),
                member.getLevel()
        );
    }

    // 아이디 찾기(searchByEmail)
    public SearchIdResponse searchIdByEmail (SearchIdRequest searchIdRequest) {
        // eamil 조회
        Optional<Member> memberOptional = memberRepository.findByEmail(searchIdRequest.getEmail());

        // 이메일 찾기 유효성
        if (memberOptional.isEmpty()) {
            return SearchIdResponse.builder()
                    .success(false)
                    .message("해당 이메일로 가입된 회원이 없습니다.")
                    .build();
        }
        Member member = memberOptional.get();

        // 찾는 이메일 있으면 아래 정보 return
        return SearchIdResponse.builder()
                .email(member.getEmail())
                .success(true)
                .message("아이디 찾기에 성공했습니다.")
                .build();
    }

    // username 조회(findByUsername)
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}
