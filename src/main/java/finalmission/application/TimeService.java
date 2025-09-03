package finalmission.application;

import finalmission.domain.holiday.service.HolidayDomainService;
import finalmission.domain.time.entity.Time;
import finalmission.domain.time.exception.TimeNotAllowedException;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import finalmission.domain.time.repository.TimeRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TimeService {

    private final TimeRepository repository;
    private final HolidayDomainService holidayDomainService;

    public Time findTime(final LocalDate localDate, final LocalTime localTime) {
        final boolean isHoliday = holidayDomainService.isHoliday(localDate);
        if (isHoliday) {
            throw new TimeNotAllowedException(localDate.toString());
        }

        final Date date = new Date(localDate);
        final StartAt startAt = new StartAt(localTime);

        return repository.findByTime(date, startAt);
    }
}
