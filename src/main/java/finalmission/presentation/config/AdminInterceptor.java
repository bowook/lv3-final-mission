package finalmission.presentation.config;

import finalmission.application.AuthService;
import finalmission.presentation.extractor.TokenExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

    private final AuthService authService;
    private final TokenExtractor tokenExtractor;

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler) throws Exception {

        final String token = tokenExtractor.extract(request.getCookies());
        authService.validateAdminByToken(token);
        return true;
    }
}
