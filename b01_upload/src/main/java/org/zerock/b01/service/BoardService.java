package org.zerock.b01.service;

import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.*;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 게시글의 이미지와 댓글의 숫자까지 처리
     * @param pageRequestDTO
     * @return
     */
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    /**
     * BoardDTO를 엔티티 객체로 변환하는 메서드
     * @param boardDTO
     * @return
     */
    default Board dtoToEntity(BoardDTO boardDTO) {
        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();

        if (boardDTO.getFileNames() != null) {
            boardDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                board.addImage(arr[0], arr[1]);
            });
        }
        return board;
    }

    /**
     * Board 엔티티 객체를 BoardDTO 타입으로 변환하는 메서드
     * @param board
     * @return
     */
    default BoardDTO entityToDTO(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();

        List<String> fileNames =
                board.getImageSet().stream().sorted().map(boardImage ->
                        boardImage.getUuid()+"_"+boardImage.getFileName()).collect(Collectors.toList());

        boardDTO.setFileNames(fileNames);

        return boardDTO;
    }
}
