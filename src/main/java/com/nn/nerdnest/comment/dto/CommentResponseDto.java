package com.nn.nerdnest.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long memberId;
    private Long parentId;
    private LocalDateTime createdAt;
}
