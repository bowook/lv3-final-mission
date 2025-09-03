package finalmission.application;

import finalmission.domain.auth.exception.AccessDeniedException;
import finalmission.domain.auth.provider.TokenProvider;
import finalmission.domain.member.exception.MemberNotFoundException;
import finalmission.domain.member.repository.MemberRepository;
import finalmission.infrastructure.auth.FakeJwtTokenProvider;
import finalmission.infrastructure.member.repository.FakeMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AuthServiceTest {

    @Autowired
    private AuthService authServiceTest;

    @Test
    void 토큰_생성() {
        // given
        final String emailValue = "email@email.com";
        final String passwordValue = "password";

        // when
        final String token = authServiceTest.createToken(emailValue, passwordValue);

        // then
        Assertions.assertThat(token).isEqualTo("memberToken");
    }

    @Test
    void 멤버가_존재하지_않으면_토큰_생성_실패() {
        // given
        final String emailValue = "invalid@email.com";
        final String passwordValue = "password";

        // when & then
        Assertions.assertThatThrownBy(
                () -> authServiceTest.createToken(emailValue, passwordValue)
        ).isInstanceOf(MemberNotFoundException.class).hasMessage("멤버가 존재하지 않아 토큰을 만들 수 없습니다.");
    }

    @Test
    void 어드민_토큰인지_검증() {
        // given
        final String token = "memberToken";

        // when & then
        Assertions.assertThatThrownBy(
                        () -> authServiceTest.validateAdminByToken(token))
                .isInstanceOf(AccessDeniedException.class)
                .hasMessage("관리자만 접근 가능합니다.");
    }

    @Test
    void 어드민_토큰이면_예외_발생X() {
        // given
        final String token = "adminToken";

        // when & then
        authServiceTest.validateAdminByToken(token);
    }

    @TestConfiguration
    static class AuthServiceTestConfiguration {

        @Bean
        public TokenProvider fakeTokenProvider() {
            return new FakeJwtTokenProvider();
        }

        @Bean
        public MemberRepository fakeMemberRepository() {
            return new FakeMemberRepository();
        }

        @Bean
        public AuthService authServiceTest() {
            return new AuthService(fakeMemberRepository(), fakeTokenProvider());
        }
    }
}
