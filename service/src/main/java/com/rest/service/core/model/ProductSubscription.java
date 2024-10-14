package com.rest.service.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@JsonInclude
@Document(collection = "ProductSubscription")
public class ProductSubscription {
    private String accountId;
    private List<String> channelId;
    private String subscriptionId;
    private String subscriptionType;
    private Instant subscriptionStartDate;
    private Instant subscriptionEndDate;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<String> getChannelId() {
        return channelId;
    }

    public void setChannelId(List<String> channelId) {
        this.channelId = channelId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Instant getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Instant subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Instant getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(Instant subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }
}
