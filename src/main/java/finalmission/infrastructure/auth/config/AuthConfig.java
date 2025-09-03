package finalmission.infrastructure.auth.config;

import finalmission.domain.auth.provider.TokenProvider;
import finalmission.infrastructure.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    private final String secretKey;
    private final long validityInMilliseconds;

    public AuthConfig(
            @Value("${security.jwt.token.secret-key}") final String secretKey,
            @Value("${security.jwt.token.expire-length}") final long validityInMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    @Bean
    public TokenProvider tokenProvider() {
        return new JwtTokenProvider(secretKey, validityInMilliseconds);
    }
}
