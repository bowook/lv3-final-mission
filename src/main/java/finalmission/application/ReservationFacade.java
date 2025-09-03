package finalmission.application;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.member.entity.Member;
import finalmission.domain.reservation.entity.Reservation;
import finalmission.domain.time.entity.Time;
import finalmission.presentation.login.dto.LoginMember;
import finalmission.presentation.reservation.dto.ReservationRequest;
import finalmission.presentation.reservation.dto.ReservationResponse;
import finalmission.presentation.reservation.dto.ReservationUpdateRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationFacade {

    private final MemberService memberService;
    private final LessonService lessonService;
    private final TimeService timeService;
    private final ReservationService reservationService;

    @Transactional
    public ReservationResponse save(final ReservationRequest request) {
        final Member member = memberService.findMember(request.name(), request.phoneNumber());
        final Lesson lesson = lessonService.findLesson(request.lesson());
        final Time time = timeService.findTime(request.date(), request.time());
        final Reservation reservation = reservationService.create(member, lesson, time);

        return ReservationResponse.from(reservation);
    }

    @Transactional
    public void delete(
            final LoginMember loginMember,
            final Long reservationId) {
        final Member member = memberService.findMemberByEmail(loginMember.email());
        reservationService.deleteReservation(member, reservationId);
    }

    @Transactional
    public ReservationResponse updateReservation(
            final Long id,
            final LoginMember loginMember,
            final ReservationUpdateRequest request) {
        final Member member = memberService.findMemberByEmail(loginMember.email());
        final Reservation reservation = reservationService.findReservationByMember(id, member);
        final Time time = timeService.findTime(request.date(), request.time());

        reservationService.updateDateAndTime(reservation, time);

        return ReservationResponse.from(reservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> getCurrentReservations() {
        final List<Reservation> reservations = reservationService.getCurrentSituations();

        return reservations.stream()
                .map(ReservationResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> getMyReservations(final Long memberId) {
        final Member member = memberService.findById(memberId);
        final List<Reservation> reservations = reservationService.getMemberReservations(member);

        return reservations.stream()
                .map(ReservationResponse::from)
                .toList();
    }
}
