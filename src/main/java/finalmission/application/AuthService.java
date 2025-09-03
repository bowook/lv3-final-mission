package finalmission.application;

import finalmission.domain.auth.exception.AccessDeniedException;
import finalmission.domain.auth.provider.TokenProvider;
import finalmission.domain.member.entity.Member;
import finalmission.domain.member.exception.MemberNotFoundException;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository repository;
    private final TokenProvider tokenProvider;

    @Transactional
    public String createToken(final String emailValue, final String passwordValue) {
        final Email email = new Email(emailValue);
        final Password password = new Password(passwordValue);

        checkInvalidLogin(email, password);

        return tokenProvider.createToken(email.getValue());
    }

    @Transactional(readOnly = true)
    public void validateAdminByToken(final String token) {
        final Email email = new Email(tokenProvider.getPayload(token));
        final Member member = repository.findByEmail(email);

        if (member.isNotAdmin()) {
            throw new AccessDeniedException("관리자만 접근 가능합니다.");
        }
    }

    private void checkInvalidLogin(final Email email, final Password password) {
        if (!repository.existsByEmailAndPassword(email, password)) {
            throw new MemberNotFoundException("멤버가 존재하지 않아 토큰을 만들 수 없습니다.");
        }
    }
}
