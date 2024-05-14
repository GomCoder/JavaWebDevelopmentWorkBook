package org.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * ModelMapper의 설정을 변경하고 쉽게 사용하기 위한 Util 클래스
 */
public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    /**
     * ModelMapper의 설정 -> private로 선언된 필드도 접근이 가능하도록 변경
     */
    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    /**
     * ModelMapper를 사용할 수 있도록 함
     * @return modelMapper
     */
    public ModelMapper get() {
        return modelMapper;
    }

}
