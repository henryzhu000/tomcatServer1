package bearServer.bearServer;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class JwtService {
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;
    private final long ttl;

    public JwtService(String secret, long ttlSeconds) {
        SecretKey key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        this.encoder = new NimbusJwtEncoder(new ImmutableSecret<>(key));
        this.decoder = NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
        this.ttl = ttlSeconds;
    }

    public String mint(String sub, Map<String, Object> claims) {
        Instant now = Instant.now();
        JwtClaimsSet.Builder b = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(ttl, ChronoUnit.SECONDS))
                .subject(sub);
        if (claims != null) claims.forEach(b::claim);

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return encoder.encode(JwtEncoderParameters.from(header, b.build())).getTokenValue();
    }

    public Jwt decode(String token) {
        return decoder.decode(token);
    }

    public long ttl() {
        return ttl;
    }
}
