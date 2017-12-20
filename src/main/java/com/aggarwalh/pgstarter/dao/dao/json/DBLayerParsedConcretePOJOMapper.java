package com.aggarwalh.pgstarter.dao.dao.json;

import com.aggarwalh.pgstarter.dao.domain.FlatObject;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Harshit Aggarwal
 */
@Mapper
public interface DBLayerParsedConcretePOJOMapper {

    String SELECT_CLAUSE= "SELECT (jdoc->>'id')::bigint as id "
            +",(jdoc->>'strAttr1')::varchar(100) as str_attr1 "
            +",(jdoc->>'strAttr2')::varchar(100) as str_attr2 "
            +",(jdoc->>'intAttr1')::int as int_attr1 "
            +",(jdoc->>'intAttr2')::int as int_attr2 "
            +",(jdoc->>'longAttr1')::bigint as long_attr1 "
            +",(jdoc->>'longAttr2')::bigint as long_attr2 "
            +",(jdoc->>'doubleAttr1')::double precision as double_attr1 "
            +",(jdoc->>'doubleAttr2')::double precision as double_attr2 "
            +",(jdoc->>'dateAttr1')::timestamp as date_attr1 "
            +",(jdoc->>'dateAttr2')::timestamp as date_attr2 ";

    @Results(id = "flatObject",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "strAttr1", column = "str_attr1"),
            @Result(property = "strAttr2", column = "str_attr2"),
            @Result(property = "intAttr1", column = "int_attr1"),
            @Result(property = "intAttr2", column = "int_attr2"),
            @Result(property = "longAttr1", column = "long_attr1"),
            @Result(property = "longAttr2", column = "long_attr2"),
            @Result(property = "doubleAttr1", column = "double_attr1"),
            @Result(property = "doubleAttr2", column = "double_attr2"),
            @Result(property = "dateAttr1", column = "date_attr1"),
            @Result(property = "dateAttr2", column = "date_attr2")
    })
    @Select(SELECT_CLAUSE + " FROM json_table WHERE ('jdoc->>'id')::bigint = #{myId}")
    FlatObject getDataById(@Param("myId") Long id);

    @ResultMap("flatObject")
    @Select(SELECT_CLAUSE + " FROM json_table")
    List<FlatObject> getAllData();
}
