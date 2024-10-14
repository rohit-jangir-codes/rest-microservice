package com.rest.service.core.service;

import com.rest.service.core.dao.TestMongoDao;
import com.rest.service.core.model.TestMongoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CreateResource")
public class CreateResourceImpl implements CreateResource {

    @Autowired
    TestMongoDao testMongoDao;

    @Autowired
    TestMongoModel testMongoModel;


    @Override
    public void createResource(String Id, String accountId, String description, String name, String type) {
        // TODO Auto-generated method stub

        testMongoModel.setId(Id);
        testMongoModel.setAccountId(accountId);
        testMongoModel.setDescription(description);
        testMongoModel.setName(name);
        testMongoModel.setType(type);

        testMongoDao.createTestService(testMongoModel);

    }
}
