package org.zerock.w2.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w2.dao.TodoDAO;
import org.zerock.w2.domain.TodoVO;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ModelMapper와 TodoDAO를 이용할 수 있도록 구성, 새로운 TodoDTO를 등록하는 기능
 */
@Log4j2
public enum TodoService {
    INSTANCE;
    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    /**
     * TodoDTO를 받아 TodoVO로 변환하여 TodoDAO를 이용하여 TodoVO를 등록함
     * @param todoDTO
     * @throws Exception
     */
    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        //System.out.println("todoVO: " + todoVO);
        log.info(todoVO);//Log정보 출력
        dao.insert(todoVO);
    }

    /**
     * TodoDAO에서 가져온 TodoVO의 목록을 모두 TodoDTO로 변환하여 반환
     * @return dtoList
     * @throws Exception
     */
    public List<TodoDTO> listAll() throws Exception {
        List<TodoVO> voList = dao.selectAll();
        log.info("voList.......................");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    /**
     * TodoDAO에서 selectOne()을 통해 TodoVO 객체를 가져옴
     * ModelMapper를 이용해서 TodoDTO로 변환함
     * @param tno
     * @return
     * @throws Exception
     */
    public TodoDTO get(Long tno) throws Exception {
        log.info("tno: " + tno);

        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    /**
     * tno를 이용하여 삭제
     * @param tno
     * @throws Exception
     */
    public void remove(Long tno) throws Exception {
        log.info("tno: " + tno);
        dao.deleteOne(tno);
    }

    /**
     * TodoDTO를 이용하여 수정
     * @param todoDTO
     * @throws Exception
     */
    public void modify(TodoDTO todoDTO) throws Exception {
        log.info("todoDTO: " + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }
}
