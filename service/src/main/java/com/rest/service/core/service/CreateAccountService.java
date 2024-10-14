package com.rest.service.core.service;

import com.rest.service.core.model.AccountModel;
import com.rest.service.core.model.ProductSubscription;

public interface CreateAccountService {
    void createAccount(AccountModel accountModel) throws Exception;
    AccountModel getAccount(String accountId);

    void updateAccount(String accountId, AccountModel accountModel);

    void updatePassword(String accountId, String oldPassword, String newPassword);

    void createSubscription(ProductSubscription productSubscription);

    ProductSubscription getSunscriptionByAccountId(String accountId);

    ProductSubscription getSubscriptionBySubscriptionId(String subscriptionId);

    void deleteSubscriptionByAccountId(String accountId);

    void deleteSubscriptionBySubscriptionId(String subscriptionId);
}
