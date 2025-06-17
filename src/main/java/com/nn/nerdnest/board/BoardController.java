package com.nn.nerdnest.board;

import com.nn.nerdnest.board.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Tag(name = "BoardController", description = "Board API")
public class BoardController {


    private final BoardService boardService;

    /*
     * 기능명 : 게시글 등록
     * URL  : /api/boards
     * 메소드 : POST
     * 요청 파라미터 : BoardCreateRequestDto
     * 응답 파라미터 : BoardCreateResponseDto
     */
    @Operation(summary = "등록" , description = "게시글을 등록합니다.")
    @PostMapping
    public ResponseEntity<BoardCreateResponseDto> createBoard(
            @RequestBody @Valid BoardCreateRequestDto boardCreateRequestDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        // 게시글 등록
        BoardCreateResponseDto boardCreateResponseDto = boardService.createBoard(boardCreateRequestDto, userDetails.getUsername());

        return ResponseEntity.ok(boardCreateResponseDto);

    }

    /*
     * 기능명 : 카테고리별 조회
     * URL  : /api/boards/category/{categoryId}
     * 메소드 : GET
     * 응답 파라미터 : BoardListResponseDto
     */
    @Operation(summary = "카테고리별 조회" , description = "카테고리별 게시글을 조회합니다.")
    @GetMapping("/category/{categoryId}")
    public Page<BoardListResponseDto> boardByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page) {
        return boardService.getBoardListByCategory(categoryId, page);
    }

    /*
     * 기능명 : 최신글 조회
     * URL  : /api/boards/latest
     * 메소드 : GET
     * 응답 파라미터 : BoardListResponseDto
     */
    @Operation(summary = "최신글 조회" , description = "최근 게시글을 조회합니다.")
    @GetMapping("/latest")
    public List<BoardListResponseDto> boardByLatest() {
        return boardService.getBoardListByCreateAt();
    }

    /*
     * 기능명 : 인기글 조회
     * URL  : /api/boards/like
     * 메소드 : GET
     * 응답 파라미터 : BoardListResponseDto
     */
    @Operation(summary = "인기글 조회" , description = "인기글을 조회합니다.")
    @GetMapping("/like")
    public List<BoardListResponseDto> boardByLike() {
        return boardService.getBoardListByLikeCount();
    }

    /*
     * 기능명 : Nerd's Kick 조회
     * URL  : /api/boards/kick
     * 메소드 : GET
     * 응답 파라미터 : BoardListResponseDto
     */
    @Operation(summary = "Nerd's kick 조회" , description = "Nerd's kick 게시글 조회합니다.")
    @GetMapping("/kick")
    public List<BoardListResponseDto> boardByViewAndLike() {
        return boardService.getBoardListByViewAndLikeCount();
    }

    /*
     * 기능명 : 게시글 상세
     * URL  : /api/boards/{boardId}
     * 메소드 : GET
     * 응답 파라미터 : BoardDetailResponseDto
     */
    @Operation(summary = "게시글 상세" , description = "게시글 상세 화면을 보여줍니다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> boardDetail(@PathVariable Long boardId) {
        BoardDetailResponseDto boardDetailResponseDto = boardService.getBoardDetail(boardId);

        return ResponseEntity.ok(boardDetailResponseDto);
    }

    /*
     * 기능명 : 게시글 수정
     * URL  : /api/boards/{boardId}
     * 메소드 : PUT
     * 요청 파라미터 : BoardUpdateRequestDto
     * 응답 파라미터 : BoardUpdateResponseDto
     */
    @Operation(summary = "게시글 수정" , description = "게시글 내용을 수정합니다.")
    @PutMapping("/{boardId}")
    public ResponseEntity<BoardUpdateResponseDto> boardUpdate(
            @PathVariable Long boardId,
            @RequestBody BoardUpdateRequestDto boardUpdateRequestDto,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        String loginUsername = userDetails.getUsername();
        BoardUpdateResponseDto responseDto = boardService.getBoardUpdate(boardId, boardUpdateRequestDto,loginUsername);
        return  ResponseEntity.ok(responseDto);
    }

    /*
     * 기능명 : 게시글 삭제
     * URL  : /api/boards/{boardId}
     * 메소드 : DELETE 
     */
    @Operation(summary = "게시글 삭제" , description = "게시글을 삭제합니다.")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> boardDelete(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        boardService.getBoardDelete(boardId, userDetails.getUsername());
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

}
