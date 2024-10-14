package com.rest.service.core.dao;

import com.rest.service.core.model.PropertiesValues;
import com.rest.service.core.model.TestMongoModel;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service("TestMongoDao")
public class TestMongoDaoImpl implements TestMongoDao {

    private static final Logger log = LoggerFactory.getLogger(TestMongoDaoImpl.class);
    @Autowired
    private TestMongoModel testMongoModel;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    PropertiesValues propertiesValues;

    static final String TEST_SERVICE_COLLECTION = "test_service";

    // create index
//    @PostConstruct
//    public void createIndex() {
//        log.info("Creating index for test service");
//        mongoTemplate.indexOps(TEST_SERVICE_COLLECTION).ensureIndex(testMongoModel.getIndexDefinition());
//    }

    @Override
    public void createTestService(TestMongoModel testMongoModel) {
        log.info("Creating test service");
        mongoTemplate.save(testMongoModel, TEST_SERVICE_COLLECTION);
    }


}
