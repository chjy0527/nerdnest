package com.nn.nerdnest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("nerdnest")
@RequiredArgsConstructor
@Tag(name = "HomeController", description = "Home API")
public class HomeController {

    @Operation(summary = "메인 페이지" , description = "메인 화면을 보여줍니다.")
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to NerdNest!", HttpStatus.OK);
    }


    @GetMapping("/")
    public String main() {
        return "hello";
    }
}
