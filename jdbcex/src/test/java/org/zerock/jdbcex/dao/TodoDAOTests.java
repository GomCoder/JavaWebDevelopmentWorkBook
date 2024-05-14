package org.zerock.jdbcex.dao;

/**
 * DAO 객체의 동작에 이상이 없는 지 테스트함
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    /**
     * 테스트 전에 TodoDAO 타입의 객체를 생성
     */
    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }

    /**
     * todoDAO에 작성한  getTime이 정상적으로 작동하는지 테스트
     * @throws Exception
     */
    @Test
    @DisplayName("현재 시간 출력 테스트")
    void testTime() throws Exception {
        System.out.println("todoDAO.getTime(): " + todoDAO.getTime()); //현재 시간을 출력해야 함
    }

    @Test
    @DisplayName("insert() 테스트")
    public void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .build();
        todoDAO.insert(todoVO);
    }

    @Test
    @DisplayName("selectAll() 테스트")
    public void testList() throws Exception {
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(
                vo -> System.out.println(vo)
        );
    }

    @Test
    @DisplayName("selectOne() 테스트")
    public void testSelectOne() throws Exception {
        Long tno = 1L;
        TodoVO vo = todoDAO.selectOne(tno);

        System.out.println(vo);
    }

    @Test
    @DisplayName("updateOne() 테스트")
    public void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021, 12, 31))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);
    }
}