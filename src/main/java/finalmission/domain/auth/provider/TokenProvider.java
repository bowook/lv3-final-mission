package finalmission.domain.auth.provider;

public interface TokenProvider {

    String createToken(final String payload);

    String getPayload(final String token);
}
