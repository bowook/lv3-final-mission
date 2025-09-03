package finalmission.presentation.resolver;

import finalmission.domain.auth.provider.TokenProvider;
import finalmission.presentation.exception.MissingTokenException;
import finalmission.presentation.extractor.TokenExtractor;
import finalmission.presentation.login.dto.LoginMember;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class LoginResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;
    private final TokenExtractor tokenExtractor;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(LoginMember.class);
    }

    @Override
    public Object resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        final String token = tokenExtractor.extract(request.getCookies());

        if (token == null || token.isBlank()) {
            throw new MissingTokenException("토큰이 존재하지 않습니다.");
        }

        final String email = tokenProvider.getPayload(token);
        return new LoginMember(email);
    }
}
