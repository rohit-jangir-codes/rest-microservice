package com.rest.service.core.dao;

import com.rest.service.core.model.EventModel;
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

@Service("CreateEventDao")
public class CreateEventDaoImpl implements CreateEventDao{
    private static final Logger log = LoggerFactory.getLogger(CreateEventDaoImpl.class);
    @Autowired
    private EventModel eventModel;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    PropertiesValues propertiesValues;

    static final String EVENT_CREATION = "event";


    @PostConstruct
    public void createIndex() {
        log.info("Creating index for channel");
        mongoTemplate.indexOps(EVENT_CREATION)
                .ensureIndex(new Index().on("eventId", Sort.Direction.ASC));
    }

    @Override
    public void createEvent(EventModel eventModel) {
        log.info("Creating event");
        mongoTemplate.save(eventModel, EVENT_CREATION);
    }

//    @Override
//    public void createEventBulk(EventModel eventModel) {
//        log.info("Creating event");
//        mongoTemplate.save(eventModel, EVENT_CREATION);
//    }

    @Override
    public boolean eventExist(String eventId) {
        return
        mongoTemplate.exists(new Query(Criteria.where("eventId").is(eventId)) , EventModel.class, EVENT_CREATION);
    }


}
