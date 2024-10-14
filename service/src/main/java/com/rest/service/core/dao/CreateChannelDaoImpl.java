package com.rest.service.core.dao;

import com.rest.service.core.model.ChannelInfo;
import com.rest.service.core.model.PropertiesValues;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service("CreateChannelDao")
public class CreateChannelDaoImpl implements CreateChannelDao {
    private static final Logger log = LoggerFactory.getLogger(CreateChannelDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PropertiesValues propertiesValues;

    @Autowired
    private ChannelInfo channelInfo;

    static final String CHANNEL_CREATION = "channel";

    @PostConstruct
    public void createIndex() {
        log.info("Creating index for channel");

        // Ensure index on the "channelId" field (ascending order)
        mongoTemplate.indexOps(CHANNEL_CREATION)
                .ensureIndex(new Index().on(PropertiesValues.CHANNEL_ID, Sort.Direction.ASC));
    }

    @Override
    public void createChannel(ChannelInfo channelInfo) {
        log.info("Creating channel");
        mongoTemplate.save(channelInfo, CHANNEL_CREATION);
    }

    @Override
    public boolean channelExist(String channelId) {
        return mongoTemplate.exists(new Query(Criteria.where("channelId").is(channelId)), ChannelInfo.class, CHANNEL_CREATION);
    }
}
