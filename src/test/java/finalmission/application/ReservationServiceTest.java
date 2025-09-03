package finalmission.application;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.member.entity.Member;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import finalmission.domain.member.model.Role;
import finalmission.domain.reservation.entity.Reservation;
import finalmission.domain.time.entity.Time;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import finalmission.infrastructure.reservation.repository.FakeReservationRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
public class ReservationServiceTest {

    private ReservationService reservationServiceTest;

    @BeforeEach
    void setUp() {
        reservationServiceTest = new ReservationService(new FakeReservationRepository());
    }

    @Test
    void 예약_생성() {
        // given
        final Member member = new Member(
                new Email("wooga@email.com"),
                new Password("password"),
                new Name("우가유저"),
                new PhoneNumber("010-1111-1111"),
                Role.USER
        );
        final Lesson lesson = new Lesson(new finalmission.domain.lesson.model.Name("하체운동"));
        final Time time = new Time(
                new Date(LocalDate.of(2025, 6, 19)),
                new StartAt(LocalTime.of(20, 20))
        );

        // when
        final Reservation reservation = reservationServiceTest.create(member, lesson, time);

        // then
        assertAll(
                () -> assertThat(reservation.getMember()).isEqualTo(member),
                () -> assertThat(reservation.getLesson()).isEqualTo(lesson),
                () -> assertThat(reservation.getTime()).isEqualTo(time)
        );
    }

    @Test
    void 예약_삭제() {
        // given
        final Member member = new Member(
                new Email("email@email.com"),
                new Password("password"),
                new Name("우가"),
                new PhoneNumber("010-1234-5678"),
                Role.USER
        );

        final Long reservationId = 1L;

        // when
        reservationServiceTest.deleteReservation(member, reservationId);

        // then
        Assertions.assertThat(reservationServiceTest.getMemberReservations(member)).isEmpty();
    }

    @Test
    void 예약_시간_날짜_변경() {
        // given
        final Member member = new Member(
                new Email("email@email.com"),
                new Password("password"),
                new Name("우가"),
                new PhoneNumber("010-1234-5678"),
                Role.USER
        );
        final Long reservationId = 1L;
        final Reservation reservation = reservationServiceTest.findReservationByMember(reservationId, member);
        final Time time = new Time(
                new Date(LocalDate.of(2025, 6, 19)),
                new StartAt(LocalTime.of(14, 14))
        );
        // when
        reservationServiceTest.updateDateAndTime(reservation, time);

        // then
        Assertions.assertThat(reservationServiceTest.findReservationByMember(reservationId, member).getTime())
                .isEqualTo(time);
    }

    @Test
    void 현재_예약_상황_조회() {
        // given
        final Member member = new Member(
                new Email("email@email.com"),
                new Password("password"),
                new Name("우가"),
                new PhoneNumber("010-1234-5678"),
                Role.USER
        );
        final Long reservationId = 1L;

        final Reservation reservation = reservationServiceTest.findReservationByMember(reservationId, member);

        // when & then
        Assertions.assertThat(reservationServiceTest.getCurrentSituations()).containsOnly(reservation);
    }

    @Test
    void 특정_멤버_예약_조회() {
        // given
        final Member member = new Member(
                new Email("email@email.com"),
                new Password("password"),
                new Name("우가"),
                new PhoneNumber("010-1234-5678"),
                Role.USER
        );

        // when
        final List<Reservation> reservations = reservationServiceTest.getMemberReservations(member);

        // then
        Assertions.assertThat(reservations).hasSize(1);
    }

    @Test
    void 특정_멤버의_예약_번호에_해당하는_예약_조회() {
        // given
        final Member member = new Member(
                new Email("email@email.com"),
                new Password("password"),
                new Name("우가"),
                new PhoneNumber("010-1234-5678"),
                Role.USER
        );
        final Long reservationId = 1L;

        // when
        final Reservation reservation = reservationServiceTest.findReservationByMember(reservationId, member);

        // then
        Assertions.assertThat(reservation.getMember().getEmail()).isEqualTo(member.getEmail());
    }
}
