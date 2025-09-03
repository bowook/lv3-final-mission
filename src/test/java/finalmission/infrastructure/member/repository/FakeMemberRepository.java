package finalmission.infrastructure.member.repository;

import finalmission.domain.member.entity.Member;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import finalmission.domain.member.model.Role;
import finalmission.domain.member.repository.MemberRepository;
import java.util.Map;
import java.util.Optional;

public class FakeMemberRepository implements MemberRepository {

    private final Map<Long, Member> database = Map.of(
            1L, new Member(
                    new Email("email@email.com"),
                    new Password("password"),
                    new Name("우가"),
                    new PhoneNumber("010-1234-5678"),
                    Role.USER
            ),
            2L, new Member(
                    new Email("admin@email.com"),
                    new Password("password"),
                    new Name("어드민우가"),
                    new PhoneNumber("010-2345-6789"),
                    Role.ADMIN
            )
    );

    @Override
    public Member findByNameAndPhoneNumber(final Name name, final PhoneNumber phoneNumber) {
        return database.values()
                .stream()
                .filter(member -> member.getName().equals(name) && member.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Optional<Member> findById(final Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Member findByEmail(final Email email) {
        return database.values()
                .stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean existsByEmailAndPassword(final Email email, final Password password) {
        return database.values()
                .stream()
                .anyMatch(member -> member.getEmail().equals(email) && member.getPassword().equals(password));
    }
}
