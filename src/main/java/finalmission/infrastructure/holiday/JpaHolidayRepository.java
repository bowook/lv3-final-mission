package finalmission.infrastructure.holiday;

import finalmission.domain.holiday.entity.Holiday;
import java.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

public interface JpaHolidayRepository extends CrudRepository<Holiday, Long> {

    boolean existsByDate(final LocalDate date);
}
