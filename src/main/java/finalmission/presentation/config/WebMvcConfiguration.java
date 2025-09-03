package finalmission.presentation.config;

import finalmission.application.AuthService;
import finalmission.domain.auth.provider.TokenProvider;
import finalmission.presentation.extractor.TokenExtractor;
import finalmission.presentation.resolver.LoginResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;
    private final TokenExtractor tokenExtractor;
    private final AuthService authService;

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginResolver(tokenProvider, tokenExtractor));
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor(authService, tokenExtractor))
                .addPathPatterns("/admin/**");
    }
}
