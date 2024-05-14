package org.zerock.b01.service;

import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.dto.ReplyDTO;

public interface ReplyService {
    /**
     * 댓글 등록 기능
     */
    Long register(ReplyDTO replyDTO);

    /**
     * 댓글 조회 기능
     */
    ReplyDTO read(Long rno);

    /**
     * 댓글 수정 기능
     * @param replyDTO
     */
    void modify(ReplyDTO replyDTO);

    /**
     * 댓글 삭제 기능
     * @param rno
     */
    void remove(Long rno);

    /**
     * 특정 게시물의 댓글 목록 처리
     * @param bno
     * @param pageRequestDTO
     * @return
     */
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
