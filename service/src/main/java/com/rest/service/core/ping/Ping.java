package com.rest.service.core.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping {

    // Create a GET API for /ping
    @GetMapping("/rest-service/admin/v1/ping")
    public String ping() {
        return "pong";
    }
}
