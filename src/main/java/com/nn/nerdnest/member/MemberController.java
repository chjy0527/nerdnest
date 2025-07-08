package com.nn.nerdnest.member;

import com.nn.nerdnest.member.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "MemberController", description = "Member API")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;


    /*
     * 기능명 : 회원가입
     * URL  : /api/members
     * 메소드 : POST
     * 요청 파라미터 : MemberRequestDto
     * 응답 파라미터 : MemberDto
     */
    @Operation(summary = "회원가입" , description = "회원가입을 합니다.")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping
    public ResponseEntity<?> registerMember(@RequestBody @Valid  MemberRequestDto memberRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", errMsg));
        }
        MemberDto registMember = memberService.saveMember(memberRequestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(registMember);
    }

    /*
     * 기능명 : 로그인
     * URL  : /api/members/auth
     * 메소드 : POST
     * 요청 파라미터 : LoginRequestDto
     * 응답 파라미터 : LoginResponseDto
     */
    @Operation(summary = "로그인" , description = "로그인을 합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @PostMapping("/auth")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        String token = memberService.login(username, password);
        Member member = memberService.findByUsername(username);

        LoginResponseDto loginResponseDto = new LoginResponseDto(
                token,
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getUsername(),
                member.getJob().getId(),
                member.getLevel(),
                member.isAgree()
        );

        return ResponseEntity.ok(loginResponseDto);
    }

    /*
     * 기능명 : 나의 정보 조회
     * URL  : /api/members/myinfo
     * 메소드 : GET
     * 응답 파라미터 : MyUserInfoResponseDto
     */
    @Operation(summary = "나의 정보" , description = "나의 정보를 찾습니다(username, job, level).")
    @ApiResponse(responseCode = "200", description = "나의 정보 찾기 성공")
    @GetMapping("/myinfo")
    public ResponseEntity<MyUserInfoResponseDto> getMyUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        MyUserInfoResponseDto myUserInfoResponseDto = memberService.myUserInfo(userDetails.getUsername());
        return ResponseEntity.ok(myUserInfoResponseDto);
    }

    /*
     * 기능명 : 아이디 찾기
     * URL  : /api/members/search-id
     * 메소드 : POST
     * 요청 파라미터 : SearchIdRequest
     * 응답 파라미터 : SearchIdResponse
     */
    @Operation(summary = "아이디 찾기" , description = "아이디를 찾습니다.")
    @ApiResponse(responseCode = "200", description = "아이디 찾기 성공")
    @PostMapping("/recovery/username")
    public ResponseEntity<SearchIdResponse> searchId(@RequestBody SearchIdRequest searchIdRequest) {
        SearchIdResponse searchIdResponse = memberService.searchIdByEmail(searchIdRequest);

        if (searchIdResponse.isSuccess()) {
            return new ResponseEntity<>(searchIdResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(searchIdResponse, HttpStatus.NOT_FOUND);
        }
    }

    /*
     * 기능명 : 비밀번호 찾기
     * URL  : /api/members/search-password
     * 메소드 : POST
     * 구현예정..
     */
    @Operation(summary = "비밀번호 찾기" , description = "비밀번호를 찾습니다.")
    @ApiResponse(responseCode = "200", description = "비밀번호 찾기 성공")
    @PostMapping("/recovery/password")
    public void searchPassword(){

    }



}
