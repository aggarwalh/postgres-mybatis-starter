package com.aggarwalh.pgstarter.dao.dao.typehandlers;

import com.aggarwalh.pgstarter.dao.utils.PostGresUtils;
import com.aggarwalh.pgstarter.dao.domain.FlatJsonWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class JsonToHashMapTypeHandler extends BaseTypeHandler<FlatJsonWrapper> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FlatJsonWrapper parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, PostGresUtils.getJsonb(parameter.getRefAttrMap(),objectMapper));
    }

    @Override
    public FlatJsonWrapper getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return deserializeToObject(rs.getString(columnName));
    }

    @Override
    public FlatJsonWrapper getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return deserializeToObject(rs.getString(columnIndex));
    }

    @Override
    public FlatJsonWrapper getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return deserializeToObject(cs.getString(columnIndex));
    }

    private FlatJsonWrapper deserializeToObject(String jsonString) throws SQLException {
        try {
            return new FlatJsonWrapper(objectMapper.readValue(jsonString, new TypeReference<HashMap<String,Object>>(){}));
        } catch (IOException e) {
            throw new SQLException("Exception while parsing jsonString to FlatJsonWrapper", e);
        }
    }
}
