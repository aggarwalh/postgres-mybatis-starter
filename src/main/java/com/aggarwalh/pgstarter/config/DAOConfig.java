package com.aggarwalh.pgstarter.config;

import com.aggarwalh.pgstarter.constants.ConfigConstants;
import com.aggarwalh.pgstarter.dao.mapper.FlatObjectMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * This class holds custom bean definitions
 *
 * @author Harshit Aggarwal
 */
@Configuration
public class DAOConfig {

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(final SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionTemplate batchSqlSessionTemplate(final SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }

    @Bean(name = ConfigConstants.FLAT_OBJECT_MAPPER)
    public MapperFactoryBean flatObjectMapper(final SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean mapper = new MapperFactoryBean();
        mapper.setMapperInterface(FlatObjectMapper.class);
        mapper.setSqlSessionTemplate(sqlSessionTemplate(sqlSessionFactory));
        return mapper;
    }


    @Bean(name = ConfigConstants.BATCHED_FLAT_OBJECT_MAPPER)
    public MapperFactoryBean batchFlatObjectMapper(final SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean mapper = new MapperFactoryBean();
        mapper.setMapperInterface(FlatObjectMapper.class);
        mapper.setSqlSessionTemplate(batchSqlSessionTemplate(sqlSessionFactory));
        return mapper;
    }


}
