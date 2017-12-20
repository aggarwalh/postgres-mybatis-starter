package com.aggarwalh.pgstarter.dao;

import com.aggarwalh.pgstarter.dao.dao.json.StringBasedJsonDataMapper;
import com.aggarwalh.pgstarter.dao.domain.FlatJsonWrapper;
import com.aggarwalh.pgstarter.dao.domain.FlatObject;
import com.aggarwalh.pgstarter.dao.domain.FlatObject2;
import com.aggarwalh.pgstarter.dao.dao.json.AppLayerParsedConcretePOJOMapper;
import com.aggarwalh.pgstarter.dao.dao.json.DBLayerParsedConcretePOJOMapper;
import com.aggarwalh.pgstarter.dao.dao.json.HashMapBasedJsonDataMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * This test case tests and documents few styles of modelling json data in application layer from db
 * <ol>
 * <li>Transform json to concrete java Type if available.
 * <ul>
 * <li>This can be achieved in two ways
 * <ol>
 * <li>Extract/Parse json in db layer. See {@link DBLayerParsedConcretePOJOMapper}</li>
 * <li>Extract/Parse json in app layer.See {@link AppLayerParsedConcretePOJOMapper}</li>
 * </ol></li>
 * <li>Works for flat and nested json. </li>
 * <li>Using {@link FlatObject} in this example </li>
 * </ul></li>
 * <li>Capture json as {@link String}, See {@link StringBasedJsonDataMapper}
 * <ul>
 * <li>Works for flat and nested json. </li>
 * <li>Used when data doesn't need to processed (like audit data) or processed lazily</li>
 * </ul></li>
 * <li>Capture flat json (non-nested) as {@link Map}<String,Object>.
 * See {@link HashMapBasedJsonDataMapper}
 * <ul>
 * <li>Works for flat and nested json. </li>
 * <li>Used when data doesn't need to processed (like audit data) or processed lazily</li>
 * </ul></li>
 * Other options not implemented here is to use other Java json representations typically provided/used by json processing libraries.
 * </ol>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PGJsonTypeDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PGJsonTypeDAOTest.class);

    @Inject
    private AppLayerParsedConcretePOJOMapper appLayerParsedConcretePOJOMapper;

    @Inject
    private DBLayerParsedConcretePOJOMapper dbLayerParsedConcretePOJOMapper;

    @Inject
    private HashMapBasedJsonDataMapper hashMapBasedJsonDataMapper;

    @Inject
    private StringBasedJsonDataMapper stringBasedJsonDataMapper;

    @Inject
    private FlatObjectMapper flatObjectMapper;

    @Test
    public void testGetDataById() throws IOException {
        FlatObject2 appLayerParsedFlatObj = appLayerParsedConcretePOJOMapper.getDataById(1l);
        FlatObject dbLayerParsedFlatObj = dbLayerParsedConcretePOJOMapper.getDataById(1l);
        FlatJsonWrapper mapRepresentation = hashMapBasedJsonDataMapper.getDataById(1l);
        String stringRepresentation = stringBasedJsonDataMapper.getDataById(1l);
    }

    @Test
    public void benchMarkAllItemSearchTime() {
        for (long count = 1; count < 20; count++) {
            StopWatch sw = new StopWatch();
            sw.start("App layer json parsing to Concrete Object");
            appLayerParsedConcretePOJOMapper.getAllData();
            sw.stop();
            sw.start("DB layer json parsing to Concrete Object");
            dbLayerParsedConcretePOJOMapper.getAllData();
            sw.stop();
            sw.start("App layer json parsing to Map based representation");
            hashMapBasedJsonDataMapper.getAllData();
            sw.stop();
            sw.start("String based representation");
            stringBasedJsonDataMapper.getAllData();
            sw.stop();
            sw.start("Concrete table to Java POJO");
            flatObjectMapper.getAllData();
            sw.stop();

            LOGGER.info(sw.getTaskInfo()[0].getTimeMillis() + "," + sw.getTaskInfo()[1].getTimeMillis() + "," +
                    sw.getTaskInfo()[2].getTimeMillis() + "," + sw.getTaskInfo()[3].getTimeMillis() + "," +
                    sw.getTaskInfo()[4].getTimeMillis());
        }
    }

    //@Before
    public void init() {
        FlatObject2 flatObject = new FlatObject2();

        for (long count = 1000; count < 10000; count++) {
            flatObject.setId(count);
            flatObject.setIntAttr1((int) (Math.random() * 10000));
            flatObject.setIntAttr2(56789);
            flatObject.setDoubleAttr1(Math.random() * 1000);
            flatObject.setDoubleAttr2(Math.random());
            flatObject.setLongAttr1(598673l);
            flatObject.setLongAttr2(2200000000l);
            flatObject.setStrAttr1("My test string1");
            flatObject.setStrAttr2("My test string2 is a bit longer");
            flatObject.setDateAttr1(new Date());
            flatObject.setDateAttr2(new Date());
            appLayerParsedConcretePOJOMapper.insertData(flatObject);
        }

    }

    @After
    public void cleanup() {
        //TODO: insert test data in table
    }
}
