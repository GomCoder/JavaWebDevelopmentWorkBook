package org.zerock.springex.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DTO를 VO로 변환하거나 VO를 DTO로 변환해야 하는 작업을 처리하기 위해 스프링 빈(Bean)으로 등록하여 처리함
 */

@Configuration
public class ModelMapperConfig {
    /**
     * ModelMapper를 반환함
     * @return modelMapper
     */
    @Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
