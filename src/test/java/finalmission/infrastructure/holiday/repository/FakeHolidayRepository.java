package finalmission.infrastructure.holiday.repository;

import finalmission.domain.holiday.entity.Holiday;
import finalmission.domain.holiday.repository.HolidayRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FakeHolidayRepository implements HolidayRepository {

    private final Map<Long, Holiday> database = new HashMap<>();
    private final AtomicLong id = new AtomicLong(1);

    public FakeHolidayRepository() {
        final Holiday initialHoliday = new Holiday(LocalDate.of(2025, 6, 6));
        database.put(id.getAndIncrement(), initialHoliday);
    }

    @Override
    public void saveHolidays(final List<Holiday> holidays) {
        for (Holiday holiday : holidays) {
            database.put(id.getAndIncrement(), holiday);
        }
    }

    @Override
    public boolean existsByDate(final LocalDate date) {
        return database.values()
                .stream()
                .anyMatch(holiday -> holiday.getDate().isEqual(date));
    }
}
