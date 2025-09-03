package finalmission.infrastructure.time;

import finalmission.domain.time.entity.Time;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import org.springframework.data.repository.CrudRepository;

public interface JpaTimeRepository extends CrudRepository<Time, Long> {
    Time findByDateAndStartAt(final Date date, final StartAt startAt);
}
