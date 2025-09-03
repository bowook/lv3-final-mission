package finalmission.application;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.member.entity.Member;
import finalmission.domain.reservation.entity.Reservation;
import finalmission.domain.reservation.repository.ReservationRepository;
import finalmission.domain.time.entity.Time;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ReservationService {

    private final ReservationRepository repository;

    public Reservation create(final Member member, final Lesson lesson, final Time time) {
        final Reservation reservation = new Reservation(member, lesson, time);

        return repository.save(reservation);
    }

    public void deleteReservation(final Member member, final Long id) {
        repository.deleteByMemberAndId(member, id);
    }

    public void updateDateAndTime(final Reservation reservation, final Time time) {
        reservation.changeTime(time);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getCurrentSituations() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Reservation> getMemberReservations(final Member member) {
        return repository.findByMember(member);
    }

    @Transactional(readOnly = true)
    public Reservation findReservationByMember(final Long id, final Member member) {
        return repository.findReservationByIdAndMember(id, member);
    }
}
