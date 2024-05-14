package org.zerock.b01.service;

import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

public interface BoardService {
    /**
     * 등록 기능
     * @param boardDTO
     * @return bno
     */
    Long register(BoardDTO boardDTO);

    /**
     *  조회 기능
     * @param bno
     * @return boardDTO
     */
    BoardDTO readOne(Long bno);


    /**
     * 수정 기능
     * @param boardDTO
     * @return
     */
    void modify(BoardDTO boardDTO);

    /**
     * 삭제 기능
     * @param bno
     */
    void remove(Long bno);

    /**
     * 목록/검색 기능
     * @param pageRequestDTO
     * @return
     */
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    /**
     * 댓글의 숫자 처리
     * @param pageRequestDTO
     * @return
     */
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
