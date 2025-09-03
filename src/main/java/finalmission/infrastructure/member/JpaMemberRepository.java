package finalmission.infrastructure.member;

import finalmission.domain.member.entity.Member;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

public interface JpaMemberRepository extends CrudRepository<Member, Long> {

    Member findByNameAndPhoneNumber(final Name name, final PhoneNumber phoneNumber);

    boolean existsByEmailAndPassword(final Email email, final Password password);

    Member findByEmail(final Email email);
}
