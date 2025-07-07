package com.nn.nerdnest.board.dto;

import com.nn.nerdnest.board.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardCreateResponseDto {
    // 게시글 등록 후 보여줄 데이터 항목
    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 제목", example = "안녕하세요! 첫 게시글 입니다.")
    private String title;

    @Schema(description = "게시글 내용", example = "백엔드 개발의 기본은 무엇일까요 ?")
    private String content;

    @Schema(description = "게시글 조회수", example = "10")
    private int view;

    @Schema(description = "게시글 좋아요 수", example = "20")
    private int likeCount;

    @Schema(description = "게시글 생성시간", example = "2025-07-08 21:09:01")
    private LocalDateTime createdAt;

    @Schema(description = "작성자", example = "김홍길")
    private String writerName;

    @Schema(description = "작성자 직업", example = "개발자")
    private String writerJob;

    @Schema(description = "작성자 연차", example = "5")
    private int writerLevel;

    @Schema(description = "카테고리 이름", example = "백앤드")
    private String categoryName;

    public BoardCreateResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.view = board.getView();
        this.likeCount = board.getLikeCount();
        this.categoryName = board.getCategory().getName();
        this.writerName = board.getMember().getUsername();
        this.writerJob = board.getMember().getJob().getName();
        this.writerLevel = board.getMember().getLevel();


    }



}
