package finalmission.infrastructure.auth;

import finalmission.domain.auth.provider.TokenProvider;
import java.util.Map;

public class FakeJwtTokenProvider implements TokenProvider {

    private final Map<String, String> tokens = Map.of(
            "email@email.com", "memberToken",
            "admin@email.com", "adminToken"
    );

    @Override
    public String createToken(final String payload) {
        return tokens.get(payload);
    }

    @Override
    public String getPayload(final String token) {
        return tokens.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(token))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }

}
