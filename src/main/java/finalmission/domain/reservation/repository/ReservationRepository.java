package finalmission.domain.reservation.repository;

import finalmission.domain.member.entity.Member;
import finalmission.domain.reservation.entity.Reservation;
import java.util.List;

public interface ReservationRepository {

    Reservation save(final Reservation reservation);

    void deleteByMemberAndId(final Member member, final Long id);

    List<Reservation> findAll();

    List<Reservation> findByMember(final Member member);

    Reservation findReservationByIdAndMember(final Long id, final Member member);
}
