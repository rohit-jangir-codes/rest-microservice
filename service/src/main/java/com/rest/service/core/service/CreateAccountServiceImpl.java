package com.rest.service.core.service;

import com.rest.service.core.dao.AccountDao;
import com.rest.service.core.model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createAccountService")
public class CreateAccountServiceImpl implements CreateAccountService {

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
}
