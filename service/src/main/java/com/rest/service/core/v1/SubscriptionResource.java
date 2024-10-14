package com.rest.service.core.v1;

import com.rest.service.core.model.ProductSubscription;
import com.rest.service.core.service.CreateAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dfc/v1/subscription")
@Slf4j
public class SubscriptionResource {


    @Autowired
    CreateAccountService createAccountService;

    @PostMapping("/create")
    public ResponseEntity<Object> createSubscription(@RequestBody ProductSubscription productSubscription) {
        try {
            createAccountService.createSubscription(productSubscription);
        } catch (Exception e) {
            log.error("Error creating subscription", e);
            return ResponseEntity.badRequest().body("Error creating subscription");
        }
        return ResponseEntity.ok().body("Subscription created successfully");
    }

    @GetMapping("/get/account/{accountId}")
    public ResponseEntity<Object> getSubscription(@PathVariable String accountId) {
        try {
            ProductSubscription productSubscription = createAccountService.getSunscriptionByAccountId(accountId);
            return ResponseEntity.ok().body(productSubscription);
        } catch (Exception e) {
            log.error("Error getting subscription", e);
            return ResponseEntity.badRequest().body("Error getting subscription");
        }
    }

    @GetMapping("/get/subscription/{subscriptionId}")
    public ResponseEntity<Object> getSubscriptionBySubscriptionId(@PathVariable String subscriptionId) {
        try {
            ProductSubscription productSubscription = createAccountService.getSubscriptionBySubscriptionId(subscriptionId);
            return ResponseEntity.ok().body(productSubscription);
        } catch (Exception e) {
            log.error("Error getting subscription", e);
            return ResponseEntity.badRequest().body("Error getting subscription");
        }
    }

    @DeleteMapping("/delete/account/{accountId}")
    public ResponseEntity<Object> deleteSubscriptionByAccountId(@PathVariable String accountId) {
        try {
            createAccountService.deleteSubscriptionByAccountId(accountId);
            return ResponseEntity.ok().body("Subscription deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting subscription", e);
            return ResponseEntity.badRequest().body("Error deleting subscription");
        }
    }

    @DeleteMapping("/delete/subscription/{subscriptionId}")
    public ResponseEntity<Object> deleteSubscriptionBySubscriptionId(@PathVariable String subscriptionId) {
        try {
            createAccountService.deleteSubscriptionBySubscriptionId(subscriptionId);
            return ResponseEntity.ok().body("Subscription deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting subscription", e);
            return ResponseEntity.badRequest().body("Error deleting subscription");
        }
    }
}
