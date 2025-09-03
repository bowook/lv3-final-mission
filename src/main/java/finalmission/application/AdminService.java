package finalmission.application;

import finalmission.domain.holiday.dto.HolidayResponses;
import finalmission.domain.holiday.entity.Holiday;
import finalmission.domain.holiday.provider.HolidayProvider;
import finalmission.domain.holiday.repository.HolidayRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final HolidayProvider provider;
    private final HolidayRepository repository;

    @Transactional
    public void saveHoliday() {
        final HolidayResponses responses = provider.generate();
        final List<Holiday> holidays = parseHolidays(responses);

        repository.saveHolidays(holidays);
    }

    private List<Holiday> parseHolidays(final HolidayResponses responses) {
        return responses.response().body().items().item().stream()
                .map(item -> {
                    final String date = item.locdate();
                    final int year = Integer.parseInt(date.substring(0, 4));
                    final int month = Integer.parseInt(date.substring(4, 6));
                    final int day = Integer.parseInt(date.substring(6, 8));

                    return LocalDate.of(year, month, day);
                })
                .toList()
                .stream()
                .map(Holiday::new)
                .toList();
    }
}
