package com.rest.service.core.v1;

import com.rest.service.core.model.EventModel;
import com.rest.service.core.model.TestMongoModel;
import com.rest.service.core.service.CreateResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/v1/event")
@Slf4j
public class EventResourceController {

    @Autowired
    CreateResource createResource;

    @PostMapping("/create")
    public ResponseEntity<Object> createEvent(@RequestBody EventModel eventModel) {
//        log.info("Create API called");
//        String id = mongoModel.getId();
//        String accountId = mongoModel.getAccountId();
//        String description = mongoModel.getDescription();
//        String name = mongoModel.getName();
//        String type = mongoModel.getType();
//
//        createResource.createResource(id, accountId, description, name, type);
        return ResponseEntity.ok().body("Resource created successfully");
    }
}
