package com.nn.nerdnest.comment;

import com.nn.nerdnest.board.Board;
import com.nn.nerdnest.board.BoardRepository;
import com.nn.nerdnest.comment.dto.*;
import com.nn.nerdnest.member.Member;
import com.nn.nerdnest.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nn.nerdnest.exception.BusinessException;
import com.nn.nerdnest.exception.ErrorCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 댓글 등록
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, String username) {
        // 작성자 조회
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        // 게시글 조회
        Board board = boardRepository.findById(commentRequestDto.getBoardId())
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_FOUND));

        // 부모 댓글이 있는 경우 (대댓글 등록)
        Comment parentComment = null;

        if(commentRequestDto.getParentId() != null) {
            parentComment = commentRepository.findById(commentRequestDto.getParentId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));

            // 대대댓글 차단 : 부모 댓글이 또 다른 부모 댓글을 가지고 있으면 예외 처리
            if(parentComment.getParent() != null ){
                throw new BusinessException(ErrorCode.TOO_DEEP_REPLY);
            }
        }

        // 댓글 생성
        Comment comment = new Comment(
                commentRequestDto.getContent(),
                member,
                board,
                parentComment
        );

        // DB 저장
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(
                saveComment.getId(),
                saveComment.getContent(),
                saveComment.getMember().getId(),
                saveComment.getParent() != null ? saveComment.getParent().getId() : null,
                saveComment.getCreatedAt()
        );
    }

    // 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentListResponseDto> getCommentList(Long boardId) {
        List<Comment>  comments = commentRepository.findByBoardId(boardId);

        // 모든 댓글을 DTO로 변환해서 Map에 저장
        Map<Long, CommentListResponseDto> dtoMap = new HashMap<>();
        List<CommentListResponseDto> result = new ArrayList<>();

        for (Comment comment : comments){
            CommentListResponseDto dto = new CommentListResponseDto(
                    comment.getId(),
                    comment.getContent(),
                    comment.getMember().getUsername(),
                    comment.getMember().getJob().getName(),
                    comment.getMember().getLevel(),
                    comment.getCreatedAt(),
                    comment.getBoard().getId(),
                    comment.getParent() != null ? comment.getParent().getId() : null
            );

            dtoMap.put(dto.getId(), dto);
        }
         for (CommentListResponseDto dto : dtoMap.values()) {
             if (dto.getParentId() == null) {
                 result.add(dto);
             } else {
                 CommentListResponseDto parent = dtoMap.get(dto.getParentId());

                 if(parent != null){
                     parent.getChildren().add(dto);
                 } else {
                     result.add(dto);
                 }
             }
         }
        return result;
    }

    // 댓글 수정
    public CommentUpdateResponseDto getUpdateComment(
            Long commentId,
            CommentUpdateRequestDto commentUpdateRequestDto,
            Member userName
    ) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getMember().getId().equals(userName.getId())){
            throw  new BusinessException(ErrorCode.COMMENT_ACCESS_DENIED);
        }

        comment.update(commentUpdateRequestDto.getContent());

        return new CommentUpdateResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getMember().getUsername(),
                comment.getUpdatedAt()
        );
    }
}
