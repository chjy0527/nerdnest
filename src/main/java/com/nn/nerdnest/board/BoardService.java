package com.nn.nerdnest.board;

import com.nn.nerdnest.board.dto.BoardCreateRequestDto;
import com.nn.nerdnest.board.dto.BoardCreateResponseDto;
import com.nn.nerdnest.board.dto.BoardListResponseDto;
import com.nn.nerdnest.member.Member;
import com.nn.nerdnest.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다,"));

        // 카테고리(categoryId) 조회
        Category category = categoryRepository.findById(boardCreateRequestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

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
}
