package bearServer.bearServer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Example public endpoint (no auth required)
    @GetMapping("/public/hello")
    public ResponseEntity<String> publicHello() {
        return ResponseEntity.ok("Hello, world (no auth needed)");
    }

    // Example secured endpoint (requires valid bearer token)
    @GetMapping("/secure/hello")
    public ResponseEntity<String> secureHello() {
        return ResponseEntity.ok("Hello, secure world (bearer token validated)");
    }
}
