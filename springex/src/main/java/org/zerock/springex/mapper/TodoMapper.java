package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    /**
     * 등록처리기능
     * @param todoVO
     */
    void insert(TodoVO todoVO);

    /**
     * 목록 기능: 가장 최근에 등록된 TodoVo가 우선적으로 나올 수 있도록 함
     * @return todoVO List
     */
    List<TodoVO> selectAll();

    /**
     * 조회 기능
     * @param tno
     * @return
     */
    TodoVO selectOne(Long tno);

    /**
     * 삭제 기능
     * @param tno
     */
    void delete(Long tno);

    /**
     * 수정 기능
     * @param todoVO
     */
    void update(TodoVO todoVO);

    /**
     * 페이징 처리
     * @param pageRequestDTO
     * @return
     */
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    /**
     * 페이지의 전체 count
     * @param pageRequestDTO
     * @return
     */
    int getCount(PageRequestDTO pageRequestDTO);
}
