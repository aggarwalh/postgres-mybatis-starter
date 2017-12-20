package com.aggarwalh.pgstarter.dao.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.PGobject;

public class PostGresUtils {

    public static PGobject getJsonb(Object obj, ObjectMapper objectMapper) {
        try {
            PGobject pgObj = new PGobject();
            pgObj.setType("jsonb");
            pgObj.setValue(obj == null ? "{}" : objectMapper.writeValueAsString(obj));
            return pgObj;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
