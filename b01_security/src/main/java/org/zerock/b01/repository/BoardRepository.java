package org.zerock.b01.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repository.search.BoardSearch;

import javax.persistence.Entity;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query(value = "SELECT NOW()", nativeQuery = true)
    String getTime();

    /**
     * 한번에 조인 처리하여 select가 이루어지도록 함
     * @param bno
     * @return
     */
    @EntityGraph(attributePaths = {"imageSet"})
    @Query("SELECT b FROM Board b WHERE b.bno = :bno")
    Optional<Board> findByIdWithImages(Long bno);
}
