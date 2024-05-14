package org.zerock.w1.todo.service;

import org.zerock.w1.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Todo 서비스 객체: 기능들의 실체 처리를 담당
 * 객체 하나만 생성하기 위해 싱글톤 패턴을 시용
 */
public enum TodoService {
    INSTANCE; //하나의 객체만을 생성해서 사용하기 위함

    /**
     * 새로운 TodoDTO 객체를 받아서 확인
     * @param todoDTO
     */
    public void register (TodoDTO todoDTO) {
        System.out.println("DEBUG......" +  todoDTO);
    }

    /**
     * 10개의 TodoDTO 객체를 만들어 반환홤
     * @return
     */
    public List<TodoDTO> getList() {
        List<TodoDTO> todoDTOS = IntStream.range(0, 10).mapToObj(i -> {
            TodoDTO dto = new TodoDTO();
            dto.setTno((long)i);
            dto.setTitle("Todo..." + i);
            dto.setDueDate(LocalDate.now());

            return dto;
        }).collect(Collectors.toList());
        return todoDTOS;
    }

    /**
     * 특정한 번호의 조회 기능
     * @param tno
     * @return
     */
    public TodoDTO get(Long tno) {
        TodoDTO dto = new TodoDTO();
        dto.setTno(tno);
        dto.setTitle("Sample Todo");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);
        return dto;
    }
}
