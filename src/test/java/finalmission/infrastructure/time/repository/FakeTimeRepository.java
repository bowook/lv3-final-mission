package finalmission.infrastructure.time.repository;

import finalmission.domain.time.entity.Time;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import finalmission.domain.time.repository.TimeRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class FakeTimeRepository implements TimeRepository {

    private final Map<Long, Time> database = Map.of(
            1L, new Time(
                    new Date(LocalDate.of(2025, 6, 18)),
                    new StartAt(LocalTime.of(20, 20))
            )
    );

    @Override
    public Time findByTime(final Date date, final StartAt startAt) {
        return database.values()
                .stream()
                .filter(time -> time.getDate().equals(date) && time.getStartAt().equals(startAt))
                .findFirst()
                .orElseThrow();
    }
}
