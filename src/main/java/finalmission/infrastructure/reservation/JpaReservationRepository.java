package finalmission.infrastructure.reservation;

import finalmission.domain.member.entity.Member;
import finalmission.domain.reservation.entity.Reservation;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface JpaReservationRepository extends ListCrudRepository<Reservation, Long> {

    void deleteByIdAndMember(final Long id, final Member member);

    List<Reservation> findByMember(final Member member);

    Reservation findByIdAndMember(final Long id, final Member member);

    boolean existsReservationByLessonId(final Long lessonId);
}
