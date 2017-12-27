package com.aggarwalh.pgstarter.dao;

import com.aggarwalh.pgstarter.domain.FlatObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Harshit Aggarwal
 */
public class TestDataUtil {

    public static List<FlatObject> getTestObjects(long count) {
        List<FlatObject> flatObjects = new ArrayList<>();
        for (long i = 1; i <= count; i++) {
            FlatObject flatObject = new FlatObject();
            flatObject.setIntAttr1((int) (Math.random() * 10000));
            flatObject.setIntAttr2(56789);
            flatObject.setDoubleAttr1(Math.random() * 1000);
            flatObject.setDoubleAttr2(Math.random());
            flatObject.setLongAttr1(598673l);
            flatObject.setLongAttr2(2200000000l);
            flatObject.setStrAttr1("My test string1");
            flatObject.setStrAttr2("My test string2 is a bit longer");
            flatObject.setDateAttr1(LocalDate.now());
            flatObject.setDateAttr2(LocalDateTime.now());
            flatObject.setBigDecimalAttr1(new BigDecimal("53.235896"));
            flatObject.setBigDecimalAttr2(new BigDecimal("3489756.3467323"));
            flatObjects.add(flatObject);
        }
        return flatObjects;
    }
}
