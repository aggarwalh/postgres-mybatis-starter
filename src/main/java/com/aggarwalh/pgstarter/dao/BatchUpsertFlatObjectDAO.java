package com.aggarwalh.pgstarter.dao;


import com.aggarwalh.pgstarter.constants.ConfigConstants;
import com.aggarwalh.pgstarter.dao.mapper.FlatObjectMapper;
import com.aggarwalh.pgstarter.domain.FlatObject;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Harshit Aggarwal
 */
@Repository
public class BatchUpsertFlatObjectDAO {

   private static final Integer batchSize = 1000;

   private static final Logger LOGGER = LoggerFactory.getLogger(BatchUpsertFlatObjectDAO.class);

    @Inject
    @Qualifier(ConfigConstants.BATCHED_FLAT_OBJECT_MAPPER)
    FlatObjectMapper batchFlatObjectMapper;

    @Transactional
    public void insert(List<FlatObject> flatObjectList){
        StopWatch sw = new StopWatch();

        List<List<FlatObject>> partitions = Lists.partition(flatObjectList, batchSize);
        Integer partitionCount = 0;

        for(List<FlatObject> partition : partitions){

            sw.start("Sending batch Partition #"+partitionCount);
            // send all statements to db
            partition.stream().forEach(o -> batchFlatObjectMapper.insertData(o));
            sw.stop();
            sw.start("Executing batch Partition #"+partitionCount);
            // execute all statements in one go
            // we shall save atleast the multiple network data transfer from db to app
            // some databases optimize this as well
            batchFlatObjectMapper.flush();
            sw.stop();
            partitionCount++;
        }
        LOGGER.info(sw.prettyPrint());
    }



}
