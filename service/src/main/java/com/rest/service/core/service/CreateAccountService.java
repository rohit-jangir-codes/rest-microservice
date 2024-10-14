package com.rest.service.core.service;

import com.rest.service.core.model.AccountModel;

public interface CreateAccountService {
    void createAccount(AccountModel accountModel) throws Exception;
    AccountModel getAccount(String accountId);
}
