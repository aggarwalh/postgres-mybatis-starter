package com.aggarwalh.pgstarter.dao.dao.json;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Harshit Aggarwal
 */
@Mapper
public interface StringBasedJsonDataMapper {

    @Select("SELECT jdoc FROM json_table WHERE ('jdoc->>'id')::bigint = #{myId}")
    String getDataById(@Param("myId") Long id);

    @Select("SELECT jdoc FROM json_table")
    List<String> getAllData();
}
