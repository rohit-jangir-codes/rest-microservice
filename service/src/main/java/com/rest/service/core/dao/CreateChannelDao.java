package com.rest.service.core.dao;

import com.rest.service.core.model.ChannelInfo;

public interface CreateChannelDao {
    void createChannel(ChannelInfo channelInfo);

    boolean channelExist(String channelId);
}
