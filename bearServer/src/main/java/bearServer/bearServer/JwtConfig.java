package bearServer.bearServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtService jwtService(
            @Value("${jwt.secret:${JWT_SECRET:}}") String secret,
            @Value("${jwt.ttl-seconds:3600}") long ttlSeconds) {

        if (secret == null || secret.isBlank()) {
            // last-resort dev default; replace for prod
            secret = "VGhpc0lzQURldk9ub__________MjM0NTY3ODkwQUJDREVGRw==";
            System.err.println("[WARN] jwt.secret not found; using built-in dev secret.");
        }
        return new JwtService(secret, ttlSeconds);
    }
}
