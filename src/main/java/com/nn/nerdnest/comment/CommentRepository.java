package com.nn.nerdnest.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT c FROM Comment c JOIN FETCH c.member WHERE c.board.id = :boardId ORDER BY c.createdAt ASC")
    List<Comment> findByBoardId(@Param("boardId") Long boardId);



}
