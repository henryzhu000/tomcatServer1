package bearServer.bearServer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwt;

    public AuthController(JwtService jwt) {
        this.jwt = jwt;
    }

    public record Login(String username, String password) {
    	
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody Login body) {
        if (!"user".equals(body.username()) || !"password".equals(body.password())) {
            return ResponseEntity.status(401).body(Map.of("error", "invalid_credentials"));
        }
        String t = jwt.mint(body.username(), Map.of("scope", "api"));
        return ResponseEntity.ok(Map.of(
                "access_token", t,
                "token_type", "Bearer",
                "expires_in", jwt.ttl()
        ));
    }
}
