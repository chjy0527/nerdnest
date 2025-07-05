package com.nn.nerdnest.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardCreateRequestDto {
    // 게시글 등록 시 입력할 데이터

    @Schema(description = "게시글 제목", example = "안녕하세요! 첫 게시글 입니다.")
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Schema(description = "게시글 내용", example = "백엔드 개발의 기본은 무엇일까요 ?")
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Schema(description = "카테고리 ID (ex : 1= 개발, 2=기획, 3=디자이너, 4=커뮤니티)", example = "1")
    @NotNull(message = "카테고리를 입력해주세요.")
    private Long categoryId;

}
