package finalmission.domain.holiday.service;

import finalmission.domain.holiday.repository.HolidayRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HolidayDomainService {

    private final HolidayRepository repository;

    public boolean isHoliday(final LocalDate date) {
        return repository.existsByDate(date);
    }
}
