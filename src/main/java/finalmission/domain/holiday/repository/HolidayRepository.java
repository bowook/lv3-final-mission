package finalmission.domain.holiday.repository;

import finalmission.domain.holiday.entity.Holiday;
import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository {


    void saveHolidays(final List<Holiday> holidays);

    boolean existsByDate(final LocalDate date);
}