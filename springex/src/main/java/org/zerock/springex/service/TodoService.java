package org.zerock.springex.service;

import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    /**
     * 등록 기능
     */
    void register(TodoDTO todoDTO);

    /**
     * 목록 기능
     * @return TodoDTO로 변환하여 반환
     */
    //List<TodoDTO> getAll();

    /**
     * 페이징 기능
     * @param pageRequestDTO
     * @return PageResponseDTO<TodoDTO>
     */
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    /**
     * 조회 기능
     * @param tno
     * @return todoDTO
     */
    TodoDTO getOne(Long tno);

    /**
     * 삭제 기능
     * @param tno
     */
    void remove(Long tno);

    /**
     * 수정 기능
     * @param todoDTO
     */
    void modify(TodoDTO todoDTO);

}
