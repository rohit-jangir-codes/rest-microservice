package com.rest.service.core.dao;

import com.rest.service.core.model.AccountModel;

public interface AccountDao {
    void createAccount(AccountModel accountModel);

    void deleteAccount(String accountId);

    void updateAccount(String accountId, AccountModel accountModel);

    AccountModel getAccount(String accountId);

    void changePassword(String accountId, String oldPassword, String newPassword);

    boolean doesAccountExist(String accountId);
}
