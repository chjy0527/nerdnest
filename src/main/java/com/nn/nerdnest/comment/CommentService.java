package com.nn.nerdnest.comment;

import com.nn.nerdnest.board.Board;
import com.nn.nerdnest.board.BoardRepository;
import com.nn.nerdnest.comment.dto.CommentRequestDto;
import com.nn.nerdnest.comment.dto.CommentResponseDto;
import com.nn.nerdnest.member.Member;
import com.nn.nerdnest.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다."));

        // 게시글 조회
        Board board = boardRepository.findById(commentRequestDto.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다."));

        // 댓글 생성
        Comment comment = new Comment(
                commentRequestDto.getContent(),
                member,
                board,
                commentRequestDto.getParentId() != null
                        ? commentRepository.findById(commentRequestDto.getParentId())
                        .orElseThrow(() -> new RuntimeException("부모 댓글 없음"))
                        : null
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
}
