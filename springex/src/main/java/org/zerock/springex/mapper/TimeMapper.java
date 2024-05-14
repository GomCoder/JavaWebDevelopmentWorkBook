package org.zerock.springex.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * 현재 시간을 처리하는 매퍼 인터페이스
 * 데이터베이스의 현재 시각을 문자열로 처리하도록 구성
 */
public interface TimeMapper {
    @Select("SELECT NOW()")
    String getTime();
}
