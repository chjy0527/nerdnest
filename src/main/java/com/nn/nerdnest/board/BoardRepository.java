package com.nn.nerdnest.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByCategory(Category category, Pageable pageable); // 카테고리별 게시글 조회
    List<Board> findTop4ByOrderByCreatedAtDesc(); // 최신글 조회
    List<Board> findTop4ByOrderByLikeCountDesc(); // 인기글 조회

    @Query("SELECT b from Board b order by (b.view + b.likeCount) DESC ")
    List<Board> findTop3ByViewAndLikeCountDesc(Pageable pageable); // nerdKick 조회
}
