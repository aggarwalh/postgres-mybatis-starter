package com.aggarwalh.pgstarter.dao.dao.json;

import com.aggarwalh.pgstarter.dao.domain.FlatJsonWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Search strategy: Fetch flat jsonb object and parse it at app layer to {@link java.util.HashMap}
 *
 * @author Harshit Aggarwal
 */
@Mapper
public interface HashMapBasedJsonDataMapper {

    @Select("SELECT jdoc FROM json_table WHERE ('jdoc->>'id')::bigint = #{myId}")
    FlatJsonWrapper getDataById(@Param("myId") Long id);

    @Select("SELECT jdoc FROM json_table")
    List<FlatJsonWrapper> getAllData();

}
