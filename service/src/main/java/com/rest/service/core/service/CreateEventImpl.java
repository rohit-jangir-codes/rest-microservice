package com.rest.service.core.service;

import com.rest.service.core.dao.CreateEventDao;
import com.rest.service.core.model.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service("CreateEvent")
public class CreateEventImpl implements CreateEvent {

    @Autowired
    CreateEventDao createEventDao;

    @Override
    public void createNewEvent(EventModel eventModel){
        boolean doesEventExist = createEventDao.eventExist(eventModel.getEventId());

        if (!doesEventExist) {

        }

    }
}
