package finalmission.domain.member.repository;

import finalmission.domain.member.entity.Member;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import java.util.Optional;

public interface MemberRepository {

    Member findByNameAndPhoneNumber(final Name name, final PhoneNumber phoneNumber);

    Optional<Member> findById(final Long id);

    Member findByEmail(final Email email);

    boolean existsByEmailAndPassword(final Email email, final Password password);
}
