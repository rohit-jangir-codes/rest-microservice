package com.rest.service.core.dao;

import com.rest.service.core.model.AccountModel;
import com.rest.service.core.model.ProductSubscription;

public interface AccountDao {
    void createAccount(AccountModel accountModel);

    void deleteAccount(String accountId);

    void updateAccount(String accountId, AccountModel accountModel);

    AccountModel getAccount(String accountId);

    void changePassword(String accountId, String oldPassword, String newPassword);

    boolean doesAccountExist(String accountId);

    void createSubscription(ProductSubscription productSubscription);

    ProductSubscription getSubscriptionByAccountId(String accountId);

    ProductSubscription getSubscriptionBySubscriptionId(String subscriptionId);

    void deleteProductSubscriptionBySubscriptionId(String subscriptionId);

    void deleteProductSubscriptionByAccountId(String accountId);
}
