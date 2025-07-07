package com.nn.nerdnest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nerdnest")
@RequiredArgsConstructor
@Tag(name = "HomeController", description = "Home API")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Operation(summary = "메인 페이지" , description = "메인 화면을 보여줍니다.")
    @ApiResponse(responseCode = "200", description = "메인화면 조회 성공")
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to NerdNest!", HttpStatus.OK);
    }


    /*
     * 기능명 : Meta Data 조회
     * URL  : /nerdnest/meta
     * 메소드 : GET
     * 응답 파라미터 : MetaDataResponseDto
     */
    @Operation(summary = "MetaData 조회" , description = "category, job 데이터 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "category, job 데이터 정보 조회 성공")
    @GetMapping("/meta")
    public MetaDataResponseDto getMetaData() {
        return homeService.getMetaData();
    }
}
