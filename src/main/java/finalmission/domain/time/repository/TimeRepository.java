package finalmission.domain.time.repository;

import finalmission.domain.time.entity.Time;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;

public interface TimeRepository {
    Time findByTime(final Date date, final StartAt startAt);
}
