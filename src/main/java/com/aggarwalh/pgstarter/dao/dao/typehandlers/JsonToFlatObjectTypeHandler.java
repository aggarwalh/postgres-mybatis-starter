package com.aggarwalh.pgstarter.dao.dao.typehandlers;

import com.aggarwalh.pgstarter.dao.config.CustomConfig;
import com.aggarwalh.pgstarter.dao.domain.FlatObject2;
import com.aggarwalh.pgstarter.dao.utils.PostGresUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonToFlatObjectTypeHandler extends BaseTypeHandler<FlatObject2> {

    // Doing manual injection as this class is created and managed by Mybatis and not Spring
    private ObjectMapper objectMapper = new CustomConfig().objectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FlatObject2 parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, PostGresUtils.getJsonb(parameter, objectMapper));
    }

    @Override
    public FlatObject2 getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return deserializeToObject(rs.getString(columnName));
    }

    @Override
    public FlatObject2 getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return deserializeToObject(rs.getString(columnIndex));
    }

    @Override
    public FlatObject2 getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return deserializeToObject(cs.getString(columnIndex));
    }

    private FlatObject2 deserializeToObject(String jsonString) throws SQLException {
        try {
            return objectMapper.readValue(jsonString, FlatObject2.class);
        } catch (IOException e) {
            throw new SQLException("Exception while parsing jsonString to FlatJsonWrapper", e);
        }
    }
}
