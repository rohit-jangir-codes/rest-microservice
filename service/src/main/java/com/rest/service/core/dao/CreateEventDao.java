package com.rest.service.core.dao;

import com.rest.service.core.model.EventModel;

public interface CreateEventDao {
    void createEvent(EventModel eventModel);

    boolean eventExist(String eventId);
}
