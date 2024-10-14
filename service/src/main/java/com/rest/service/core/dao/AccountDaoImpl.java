package com.rest.service.core.dao;

import com.rest.service.core.model.AccountModel;
import com.rest.service.core.model.PropertiesValues;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service("AccountDao")
public class AccountDaoImpl implements AccountDao {

    private static final Logger log = LoggerFactory.getLogger(CreateChannelDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    AccountModel accountModel;

    private static final String ACCOUNT_CREATION = "account";

    private static final String PRODUCT_SUBSCRIPTION = "subscription";

    @PostConstruct
    public void createIndex() {
        log.info("Creating index for channel");

        // Ensure index on the "channelId" field (ascending order)
        mongoTemplate.indexOps(ACCOUNT_CREATION)
                .ensureIndex(new Index()
                        .on(PropertiesValues.ACCOUNT_ID, Sort.Direction.ASC));
        mongoTemplate.indexOps(ACCOUNT_CREATION)
                .ensureIndex(new Index()
                        .on(PropertiesValues.User_Email, Sort.Direction.ASC));
        mongoTemplate.indexOps(ACCOUNT_CREATION)
                .ensureIndex(new Index()
                        .on(PropertiesValues.Phone_Number, Sort.Direction.ASC));
        mongoTemplate.indexOps(ACCOUNT_CREATION)
                .ensureIndex(new Index()
                        .on(PropertiesValues.ACCOUNT_ID, Sort.Direction.ASC)
                        .on(PropertiesValues.User_Email, Sort.Direction.ASC)
                        .on(PropertiesValues.Phone_Number, Sort.Direction.ASC));
        mongoTemplate.indexOps(PRODUCT_SUBSCRIPTION)
                .ensureIndex(new Index()
                        .on(PropertiesValues.ACCOUNT_ID, Sort.Direction.ASC));
    }

    @Override
    public void createAccount(AccountModel accountModel) {
        try {
            mongoTemplate.save(accountModel, ACCOUNT_CREATION);
            log.info("Account created");
        } catch (Exception e) {
            log.error("Error creating account", e);
        }
    }

    @Override
    public void deleteAccount(String accountId) {
        mongoTemplate.remove(accountId, ACCOUNT_CREATION);
        log.info("Account deleted");
    }

    @Override
    public void updateAccount(String accountId, AccountModel accountModel) {

        Update update = new Update();
        update.set(PropertiesValues.Account_Name, accountModel.getAccountName());
        update.set(PropertiesValues.Account_Type, accountModel.getAccountType());
        update.set(PropertiesValues.User_Password, accountModel.getPassword());

        mongoTemplate.updateFirst(new Query(Criteria.where("accountId").is(accountId)), update, ACCOUNT_CREATION);
        System.out.println("Account updated");
    }

    @Override
    public AccountModel getAccount(String accountId) {
        return mongoTemplate.findOne(
                new Query(Criteria.where(PropertiesValues.Account_ID).is(accountId)),
                AccountModel.class,
                ACCOUNT_CREATION
        );
    }


    @Override
    public void changePassword(String accountId, String oldPassword, String newPassword) {
        Update update = new Update();
        update.set(PropertiesValues.User_Password, newPassword);
        mongoTemplate.updateFirst(new Query(Criteria.where(PropertiesValues.ACCOUNT_ID).is(accountId).and(PropertiesValues.User_Password).is(oldPassword)), update, ACCOUNT_CREATION);
        System.out.println("Password updated");
    }

    @Override
    public boolean doesAccountExist(String accountId) {
        return mongoTemplate.exists(new Query(Criteria.where(PropertiesValues.ACCOUNT_ID).is(accountId)), AccountModel.class, ACCOUNT_CREATION);
    }
}
