package com.aggarwalh.pgstarter.dao;

import com.aggarwalh.pgstarter.dao.mapper.FlatObjectMapper;
import com.aggarwalh.pgstarter.domain.FlatObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatObjectDAOTest {

    @Inject
    FlatObjectMapper flatObjectMapper;

    @Test
    public void testBasicCRUDOperations() {
        // create test object
        FlatObject inputFlatObject = TestDataUtil.getTestObjects(1).get(0);
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

}
