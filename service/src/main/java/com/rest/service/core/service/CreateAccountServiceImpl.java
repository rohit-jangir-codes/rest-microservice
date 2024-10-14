package com.rest.service.core.service;

import com.rest.service.core.dao.AccountDao;
import com.rest.service.core.dao.CreateChannelDaoImpl;
import com.rest.service.core.model.AccountModel;
import com.rest.service.core.model.ProductSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createAccountService")
public class CreateAccountServiceImpl implements CreateAccountService {
    private static final Logger log = LoggerFactory.getLogger(CreateChannelDaoImpl.class);


    @Autowired
    AccountDao accountDao;

    @Override
    public void createAccount(AccountModel accountModel) throws Exception {
        if (!accountDao.doesAccountExist(accountModel.getAccountId())) {
            accountDao.createAccount(accountModel);
        } else
        {
            throw new Exception("Account already exists");
        }
    }

    @Override
    public AccountModel getAccount(String accountId) {
        return accountDao.getAccount(accountId);
    }

    @Override
    public void updateAccount(String accountId, AccountModel accountModel) {
        accountDao.updateAccount(accountId, accountModel);
    }

    @Override
    public void updatePassword(String accountId, String oldPassword, String newPassword) {
        accountDao.changePassword(accountId, oldPassword, newPassword);
    }

    @Override
    public void createSubscription(ProductSubscription productSubscription) {
        try {
            if (!accountDao.doesAccountExist(productSubscription.getAccountId())) {
                accountDao.createSubscription(productSubscription);
            }
        } catch (Exception e) {
            log.info("Error creating subscription");
            e.printStackTrace();
        }

    }

    @Override
    public ProductSubscription getSunscriptionByAccountId(String accountId) {
        return  accountDao.getSubscriptionByAccountId(accountId);
    }

    @Override
    public ProductSubscription getSubscriptionBySubscriptionId(String subscriptionId) {
        return accountDao.getSubscriptionBySubscriptionId(subscriptionId);
    }

    @Override
    public void deleteSubscriptionByAccountId(String accountId) {
        accountDao.deleteProductSubscriptionByAccountId(accountId);
    }

    @Override
    public void deleteSubscriptionBySubscriptionId(String subscriptionId) {
        accountDao.deleteProductSubscriptionBySubscriptionId(subscriptionId);
    }
}
