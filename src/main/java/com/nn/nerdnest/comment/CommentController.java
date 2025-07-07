package com.nn.nerdnest.comment;

import com.nn.nerdnest.comment.dto.CommentRequestDto;
import com.nn.nerdnest.comment.dto.CommentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
