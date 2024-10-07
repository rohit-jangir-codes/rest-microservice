package ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping {

    // Create a GET API for /ping
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
