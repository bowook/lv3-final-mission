package finalmission.infrastructure.holiday;

import finalmission.domain.holiday.entity.Holiday;
import finalmission.domain.holiday.repository.HolidayRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HolidayRepositoryImpl implements HolidayRepository {

    private final JpaHolidayRepository repository;

    @Override
    public void saveHolidays(final List<Holiday> holidays) {
        repository.saveAll(holidays);
    }

    @Override
    public boolean existsByDate(final LocalDate date) {
        return repository.existsByDate(date);
    }
}
