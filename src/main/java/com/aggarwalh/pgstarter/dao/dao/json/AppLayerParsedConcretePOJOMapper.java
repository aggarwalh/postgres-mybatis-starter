package com.aggarwalh.pgstarter.dao.dao.json;

import com.aggarwalh.pgstarter.dao.dao.typehandlers.JsonToFlatObjectTypeHandler;
import com.aggarwalh.pgstarter.dao.domain.FlatObject;
import com.aggarwalh.pgstarter.dao.domain.FlatObject2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Search strategy: Fetch flat jsonb object and parse it at app layer to {@link FlatObject}
 *
 * @author Harshit Aggarwal
 */
@Mapper
public interface AppLayerParsedConcretePOJOMapper {

    /**
     * Stuff to note here.
     * <ul>
     * <li> <b>Input</b>: Need for @Param even with single parameter.
     * Mybatis is NOT able to substitute param correctly without explicit @Param. (atleast as of 3.4.5)
     * </li>
     * <li><b>Query</b>: 'jdoc->>'id' extracts data as text and cast it to bigint (sql version for long)</li>
     * <li><b>Output</b>: We have provided a TypeHandler ({@link JsonToFlatObjectTypeHandler})
     * with serializes json to {@link FlatObject2} using jackson.
     * </li>
     * </ul>
     */
    @Select("SELECT jdoc FROM json_table WHERE ('jdoc->>'id')::bigint = #{myId}")
    FlatObject2 getDataById(@Param("myId") Long id);

    @Select("SELECT jdoc FROM json_table")
    List<FlatObject2> getAllData();

    @Insert("INSERT INTO json_table(jdoc) VALUES (#{flatObject})")
    void insertData(FlatObject2 flatObject);
}
