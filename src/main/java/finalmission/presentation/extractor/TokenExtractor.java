package finalmission.presentation.extractor;

import finalmission.presentation.exception.MissingTokenException;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class TokenExtractor {

    private static final String TOKEN_COOKIE_NAME = "token";

    public String extract(final Cookie[] cookies) {
        if (cookies == null) {
            throw new MissingTokenException("토큰이 존재하지 않습니다.");
        }

        for (Cookie cookie : cookies) {
            if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        throw new MissingTokenException("토큰이 존재하지 않습니다.");
    }
}
