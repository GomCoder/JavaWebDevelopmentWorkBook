package org.zerock.api01.respository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.TodoDTO;

/**
 * 단순 페이지 처리 기능
 */
public interface TodoSearch {
   Page<TodoDTO> list(PageRequestDTO pageRequestDTO);


}
