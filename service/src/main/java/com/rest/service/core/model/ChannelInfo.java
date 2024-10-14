package com.rest.service.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.Instant;

@JsonInclude
@Component
@Document(collection = "ChannelCreation")
public class ChannelInfo {

    @Id
    private String channelId;
    private String channelName;
    private String channelType;
    private Instant avalibilityStart;
    private Instant avalibilityEnd;
    private String channelRating;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Instant getAvalibilityStart() {
        return avalibilityStart;
    }

    public void setAvalibilityStart(Instant avalibilityStart) {
        this.avalibilityStart = avalibilityStart;
    }

    public Instant getAvalibilityEnd() {
        return avalibilityEnd;
    }

    public void setAvalibilityEnd(Instant avalibilityEnd) {
        this.avalibilityEnd = avalibilityEnd;
    }

    public String getChannelRating() {
        return channelRating;
    }

    public void setChannelRating(String channelRating) {
        this.channelRating = channelRating;
    }
}
