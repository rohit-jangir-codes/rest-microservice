package com.rest.service.core.v1;

import com.rest.service.core.model.TestMongoModel;
import com.rest.service.core.service.CreateResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/v1/admin")
@Slf4j
public class ApiResources {

    @Autowired
    CreateResource createResource;

    @Autowired
    TestMongoModel testMongoModel;

    @PostMapping("/create")
    public ResponseEntity<Object> createResource(@RequestBody TestMongoModel mongoModel) {
        log.info("Create API called");
        String id = mongoModel.getId();
        String accountId = mongoModel.getAccountId();
        String description = mongoModel.getDescription();
        String name = mongoModel.getName();
        String type = mongoModel.getType();

        createResource.createResource(id, accountId, description, name, type);
        return ResponseEntity.ok().body("Resource created successfully");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working");
    }


}
