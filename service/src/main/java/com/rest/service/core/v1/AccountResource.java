package com.rest.service.core.v1;

import com.rest.service.core.model.AccountModel;
import com.rest.service.core.service.CreateAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/v1/account")
@Slf4j
public class AccountResource {

    @Autowired
    CreateAccountService createAccountService;

    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(@RequestBody AccountModel accountModel) throws Exception {
        createAccountService.createAccount(accountModel);
        log.info("Create API called");
        return ResponseEntity.ok().body("Account created successfully");
    }

    @GetMapping("/get/{accountId}")
    public ResponseEntity<Object> getAccount(@PathVariable String accountId) {
        try {
            AccountModel accountModel = createAccountService.getAccount(accountId);
            return ResponseEntity.ok().body(accountModel);
        } catch (Exception e) {
            log.error("Error getting account", e);
            return ResponseEntity.badRequest().body("Error getting account");
        }
    }
}
