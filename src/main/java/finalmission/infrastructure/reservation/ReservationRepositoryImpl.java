package finalmission.infrastructure.reservation;

import finalmission.domain.member.entity.Member;
import finalmission.domain.reservation.entity.Reservation;
import finalmission.domain.reservation.repository.ReservationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final JpaReservationRepository repository;

    @Override
    public Reservation save(final Reservation reservation) {
        return repository.save(reservation);
    }

    @Override
    public void deleteByMemberAndId(final Member member, final Long id) {
        repository.deleteByIdAndMember(id, member);
    }

    @Override
    public Reservation findReservationByIdAndMember(final Long id, final Member member) {
        return repository.findByIdAndMember(id, member);
    }

    @Override
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Reservation> findByMember(final Member member) {
        return repository.findByMember(member);
    }
}
