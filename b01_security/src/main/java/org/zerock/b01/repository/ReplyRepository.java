package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    /**
     * 특정한 게시글의 댓글들은 페이징 처리
     * @param bno
     * @param pageable
     * @return
     */
    @Query("SELECT r FROM Reply r WHERE r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);

    /**
     * 게시물과 첨부파일 삭제
     * @param bno
     */
    void deleteByBoard_Bno(Long bno);
}
