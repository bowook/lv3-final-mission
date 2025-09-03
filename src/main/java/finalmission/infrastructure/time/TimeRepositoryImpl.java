package finalmission.infrastructure.time;

import finalmission.domain.time.entity.Time;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import finalmission.domain.time.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TimeRepositoryImpl implements TimeRepository {

    private final JpaTimeRepository repository;

    @Override
    public Time findByTime(final Date date, final StartAt startAt) {
        return repository.findByDateAndStartAt(date, startAt);
    }
}
