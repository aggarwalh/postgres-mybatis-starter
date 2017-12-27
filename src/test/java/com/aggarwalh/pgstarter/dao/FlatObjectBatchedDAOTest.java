package com.aggarwalh.pgstarter.dao;

import com.aggarwalh.pgstarter.constants.ConfigConstants;
import com.aggarwalh.pgstarter.dao.mapper.FlatObjectMapper;
import com.aggarwalh.pgstarter.domain.FlatObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Harshit Aggarwal
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatObjectBatchedDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlatObjectBatchedDAOTest.class);

    /**
     * This one performs batched upsert operations
     */
    @Inject
    BatchUpsertFlatObjectDAO batchFlatObjectDAO;

    /**
     * This one performs upsert operations iteratively
     */
    @Inject
    @Qualifier(ConfigConstants.FLAT_OBJECT_MAPPER)
    FlatObjectMapper flatObjectMapper;

    /**
     * Analyze iterative vs batch insert performance in postgresql
     */
    @Test
    public void testBatchedVsIterativeInsertPerformance() {
        long testCount = 100;
        // create test object
        List<FlatObject> inputFlatObjects = TestDataUtil.getTestObjects(testCount);

        StopWatch sw = new StopWatch();
        sw.start("Batched Data Insert");
        // insert data
        batchFlatObjectDAO.insert(inputFlatObjects);
        sw.stop();
        sw.start("Iterative Data Insert");
        inputFlatObjects.forEach(o -> flatObjectMapper.insertData(o));
        sw.stop();
        LOGGER.info("Batch vs Iterative Insert Stats: "+ sw.prettyPrint());
    }


}
