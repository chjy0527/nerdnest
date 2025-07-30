package com.nn.nerdnest.comment;

import com.nn.nerdnest.CustomUserDetails;
import com.nn.nerdnest.comment.dto.*;
import com.nn.nerdnest.member.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "CommentController", description = "Comment API")
public class CommentController {

    private final CommentService commentService;

    /*
     * 기능명 : 댓글 등록
     * URL  : /api/comments
     * 메소드 : POST
     * 요청 파라미터 : CommentRequestDto
     * 응답 파라미터 : CommentResponseDto
     */
    @Operation(summary = "댓글등록" , description = "댓글을 등록 합니다.")
    @ApiResponse(responseCode = "200", description = "댓글 등록 성공")
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto, userDetails.getUsername());
        return ResponseEntity.ok(commentResponseDto);
    }

    /*
     * 기능명 : 댓글 조회
     * URL  : /api/comments/{boardId}
     * 메소드 : GET
     * 응답 파라미터 : CommentListResponseDto
     */
    @Operation(summary ="댓글 조회", description = "댓글을 조회 합니다.")
    @ApiResponse(responseCode = "200", description = "댓글 조회 성공")
    @GetMapping("{boardId}")
    public ResponseEntity<List<CommentListResponseDto>> listComment(@PathVariable Long boardId) {
        List<CommentListResponseDto> comments = commentService.getCommentList(boardId);
        return ResponseEntity.ok(comments);

    }

    /*
     * 기능명 : 댓글 수정
     * URL  : /api/comments/{boardId}
     * 메소드 : PUT
     * 요청 파라미터 : CommentUpdateRequestDto
     * 응답 파라미터 : CommentUpdateResponseDto
     */
    @Operation(summary ="댓글 수정", description = "댓글을 수정 합니다.")
    @ApiResponse(responseCode = "200", description = "댓글 수정 성공")
    @PutMapping("{commentId}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentUpdateRequestDto commentUpdateRequestDto,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            ) {
        Member loginName = customUserDetails.getCommentMember();
        CommentUpdateResponseDto requestDto =
                commentService.getUpdateComment(commentId, commentUpdateRequestDto, loginName );
        return ResponseEntity.ok(requestDto);

    }



}
