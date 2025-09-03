package finalmission.infrastructure.member;

import finalmission.domain.member.entity.Member;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import finalmission.domain.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository repository;

    @Override
    public Member findByNameAndPhoneNumber(final Name name, final PhoneNumber phoneNumber) {
        return repository.findByNameAndPhoneNumber(name, phoneNumber);
    }

    @Override
    public Optional<Member> findById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public Member findByEmail(final Email email) {
        return repository.findByEmail(email);
    }

    @Override
    public boolean existsByEmailAndPassword(final Email email, final Password password) {
        return repository.existsByEmailAndPassword(email, password);
    }
}
