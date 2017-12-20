package com.aggarwalh.pgstarter.dao;

import com.aggarwalh.pgstarter.dao.domain.FlatObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatObjectDAOTest {

    @Inject
    FlatObjectMapper flatObjectMapper;

    @Test
    public void testBasicCRUDOperations() {
        // create test object
        FlatObject inputFlatObject = getTestObject();
        // insert data
        long updatedCount = flatObjectMapper.insertData(inputFlatObject);
        Assert.assertEquals(1l,updatedCount);

        // fetch data from db
        FlatObject dbFetchedObject = flatObjectMapper.getDataById(inputFlatObject.getId());
        Assert.assertNotNull(dbFetchedObject);
        Assert.assertEquals(inputFlatObject, dbFetchedObject);

        // delete data point
        flatObjectMapper.deleteDataById(inputFlatObject.getId());
        FlatObject dbFetchedObjectAfterDeletion = flatObjectMapper.getDataById(inputFlatObject.getId());
        Assert.assertNull(dbFetchedObjectAfterDeletion);
    }

    private FlatObject getTestObject() {
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
        return flatObject;
    }

}
