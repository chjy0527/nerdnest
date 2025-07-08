package com.nn.nerdnest.board;

import com.nn.nerdnest.board.dto.*;
import com.nn.nerdnest.member.Member;
import com.nn.nerdnest.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import com.nn.nerdnest.exception.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    // 게시글 등록(createBoard)
    @Transactional
    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto, String username) {
        // 사용자(username) 조회
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        // 카테고리(categoryId) 조회
        Category category = categoryRepository.findById(boardCreateRequestDto.getCategoryId())
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));

        // 사용자가 입력한 게시글 데이터
        Board  board = new Board(
                boardCreateRequestDto.getTitle(),
                boardCreateRequestDto.getContent(),
                member,
                category);

        // 게시글 등록
        Board createdBoard = boardRepository.save(board);

        return new BoardCreateResponseDto(createdBoard);

    }

    // 카테고리별 게시글 조회 (page -> 9개씩)
    public Page<BoardListResponseDto> getBoardListByCategory(Long categoryId, int page) {
        // 카테고리 (categoryId) 조회
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));

        //  pageing : categoryId, 9개씩
        Page<Board> boards = boardRepository.findByCategory(category, PageRequest.of(page, 9));
        return boards.map(BoardListResponseDto::new);
    }

    // 최신글 조회 (4개씩)
    public List<BoardListResponseDto> getBoardListByCreateAt() {
        return boardRepository.findTop4ByOrderByCreatedAtDesc()
                .stream().map(BoardListResponseDto::new).collect(Collectors.toList());
    }

    // 인기글 조회 (4개씩)
    public List<BoardListResponseDto> getBoardListByLikeCount() {
        return boardRepository.findTop4ByOrderByLikeCountDesc()
                .stream().map(BoardListResponseDto::new).collect(Collectors.toList());
    }

    //  nerd's kick 조회 -> 조회수 + 좋아요 가장 높은 게시글 (3개씩)
    public List<BoardListResponseDto> getBoardListByViewAndLikeCount() {
        Pageable pageable = PageRequest.of(0, 3);
        return boardRepository.findTop3ByViewAndLikeCountDesc(pageable)
                .stream().map(BoardListResponseDto::new).collect(Collectors.toList());
    }

    // 게시글 상세
    public BoardDetailResponseDto getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_FOUND));
        return new BoardDetailResponseDto(board.getId(), board.getTitle(), board.getContent());
    }

    // 게시글 수정
    @Transactional
    public BoardUpdateResponseDto getBoardUpdate(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto, String loginUser) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_FOUND));

        String writerUsername = board.getMember().getUsername();

        // 작성자 유효성
        if (!writerUsername.equals(loginUser)) {
            throw new BusinessException(ErrorCode.BOARD_ACCESS_DENIED, "작성자만 수정할 수 있습니다.");
        }

        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());

        return new BoardUpdateResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember().getUsername(),
                board.getUpdatedAt()
        );
    }

    // 게시글 삭제
    @Transactional
    public void  getBoardDelete(Long boardId, String username) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if(!board.getMember().getUsername().equals(username)) {
            throw new AccessDeniedException("게시글 삭제 권한이 없습니다.");
        }
        boardRepository.delete(board);
    }

}
