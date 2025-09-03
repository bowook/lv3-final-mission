package finalmission.domain.member.entity;

import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import finalmission.domain.member.model.Role;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(
            final Email email,
            final Password password,
            final Name name,
            final PhoneNumber phoneNumber,
            final Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public boolean isNotAdmin() {
        return role.equals(Role.USER);
    }
}
