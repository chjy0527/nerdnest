package com.nn.nerdnest.board;

import com.nn.nerdnest.board.dto.BoardCreateRequestDto;
import com.nn.nerdnest.board.dto.BoardCreateResponseDto;
import com.nn.nerdnest.member.Member;
import com.nn.nerdnest.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    // 게시글 등록
    @Transactional
    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto, String username) {
        System.out.println(boardCreateRequestDto);
        System.out.println("들어옴");
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다,"));

        Category category = categoryRepository.findById(boardCreateRequestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        Board  board = new Board(
                boardCreateRequestDto.getTitle(),
                boardCreateRequestDto.getContent(),
                member,
                category);

        Board createdBoard = boardRepository.save(board);

        return new BoardCreateResponseDto(createdBoard);

    }



}
