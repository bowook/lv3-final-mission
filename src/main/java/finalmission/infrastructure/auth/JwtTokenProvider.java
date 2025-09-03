package finalmission.infrastructure.auth;

import finalmission.domain.auth.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider {

    private final String secretKey;
    private final long validityInMilliseconds;

    @Override
    public String createToken(final String payload) {
        final Claims claims = Jwts.claims().setSubject(payload);
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public String getPayload(final String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
