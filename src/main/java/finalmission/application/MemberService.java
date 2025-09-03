package finalmission.application;

import finalmission.domain.member.entity.Member;
import finalmission.domain.member.exception.MemberNotFoundException;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.PhoneNumber;
import finalmission.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;

    public Member findMember(final String nameValue, final String phoneNumberValue) {
        final Name name = new Name(nameValue);
        final PhoneNumber phoneNumber = new PhoneNumber(phoneNumberValue);

        return repository.findByNameAndPhoneNumber(name, phoneNumber);
    }

    public Member findById(final Long memberId) {
        return repository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다.")
                );
    }

    public Member findMemberByEmail(final String emailValue) {
        final Email email = new Email(emailValue);

        return repository.findByEmail(email);
    }
}
