package com.nn.nerdnest.board;

import com.nn.nerdnest.board.dto.BoardCreateRequestDto;
import com.nn.nerdnest.board.dto.BoardCreateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Tag(name = "BoardController", description = "Board API")
public class BoardController {


    private final BoardService boardService;

    @Operation(summary = "게시글 리스트" , description = "카테고리별 게시글 리스트를 보여줍니다.")
    @GetMapping("/list")
    public void list(){

    }

    @Operation(summary = "등록" , description = "게시글을 등록합니다.")
    @PostMapping
    public ResponseEntity<BoardCreateResponseDto> createBoard(
            @RequestBody @Valid BoardCreateRequestDto boardCreateRequestDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("들어옴");
        BoardCreateResponseDto boardCreateResponseDto = boardService.createBoard(boardCreateRequestDto, userDetails.getUsername());
        return ResponseEntity.ok(boardCreateResponseDto);

    }



    @Operation(summary = "상세" , description = "게시글 상세 화면을 보여줍니다.")
    @PostMapping("/detail")
    public void detail(){

    }

    @Operation(summary = "수정" , description = "게시글 내용을 수정합니다.")
    @PostMapping("/update")
    public void update(){

    }

    @Operation(summary = "삭제" , description = "게시글 내용을 삭제합니다.")
    @PostMapping("/delete")
    public void delete(){

    }

}
