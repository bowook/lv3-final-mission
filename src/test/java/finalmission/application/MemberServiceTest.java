package finalmission.application;

import finalmission.domain.member.entity.Member;
import finalmission.domain.member.exception.MemberNotFoundException;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import finalmission.domain.member.model.Role;
import finalmission.domain.member.repository.MemberRepository;
import finalmission.infrastructure.member.repository.FakeMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

    @Autowired
    private MemberService memberServiceTest;

    @Test
    void 회원_찾기() {
        // given
        final String nameValue = "우가";
        final String phoneNumberValue = "010-1234-5678";
        final Name name = new Name(nameValue);
        final PhoneNumber phoneNumber = new PhoneNumber(phoneNumberValue);

        // when
        final Member member = memberServiceTest.findMember(nameValue, phoneNumberValue);

        // then
        assertAll(
                () -> assertThat(member.getName()).isEqualTo(name),
                () -> assertThat(member.getPhoneNumber()).isEqualTo(phoneNumber)
        );
    }

    @Test
    void 식별자를_기준으로_회원_찾기() {
        // given
        final Long memberId = 1L;
        final Email expectedEmail = new Email("email@email.com");
        final Password expectedPassword = new Password("password");
        final Name expectedName = new Name("우가");
        final PhoneNumber expectedPhoneNumber = new PhoneNumber("010-1234-5678");
        final Role expectedRole = Role.USER;

        // when
        final Member member = memberServiceTest.findById(memberId);

        // then
        assertAll(
                () -> assertThat(member.getEmail()).isEqualTo(expectedEmail),
                () -> assertThat(member.getPassword()).isEqualTo(expectedPassword),
                () -> assertThat(member.getName()).isEqualTo(expectedName),
                () -> assertThat(member.getPhoneNumber()).isEqualTo(expectedPhoneNumber),
                () -> assertThat(member.getRole()).isEqualTo(expectedRole)
        );
    }

    @Test
    void 식별자를_기준으로_회원_찾기_실패시_예외_발생() {
        // given
        final Long memberId = 3L;

        // when & then
        Assertions.assertThatThrownBy(
                () -> memberServiceTest.findById(memberId)
        ).isInstanceOf(MemberNotFoundException.class).hasMessage("멤버를 찾을 수 없습니다.");
    }

    @Test
    void 이메일을_기준으로_회원_찾기() {
        // given
        final String emailValue = "email@email.com";
        final Email expectedEmail = new Email("email@email.com");
        final Password expectedPassword = new Password("password");
        final Name expectedName = new Name("우가");
        final PhoneNumber expectedPhoneNumber = new PhoneNumber("010-1234-5678");
        final Role expectedRole = Role.USER;

        // when
        final Member member = memberServiceTest.findMemberByEmail(emailValue);

        // then
        assertAll(
                () -> assertThat(member.getEmail()).isEqualTo(expectedEmail),
                () -> assertThat(member.getPassword()).isEqualTo(expectedPassword),
                () -> assertThat(member.getName()).isEqualTo(expectedName),
                () -> assertThat(member.getPhoneNumber()).isEqualTo(expectedPhoneNumber),
                () -> assertThat(member.getRole()).isEqualTo(expectedRole)
        );
    }

    @TestConfiguration
    static class MemberServiceTestConfiguration {

        @Bean
        public MemberRepository fakeMemberRepository() {
            return new FakeMemberRepository();
        }

        @Bean
        public MemberService memberServiceTest() {
            return new MemberService(fakeMemberRepository());
        }
    }
}
