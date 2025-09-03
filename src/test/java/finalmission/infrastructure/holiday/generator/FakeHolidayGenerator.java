package finalmission.infrastructure.holiday.generator;

import finalmission.domain.holiday.dto.HolidayBody;
import finalmission.domain.holiday.dto.HolidayItem;
import finalmission.domain.holiday.dto.HolidayItems;
import finalmission.domain.holiday.dto.HolidayResponse;
import finalmission.domain.holiday.dto.HolidayResponses;
import finalmission.domain.holiday.provider.HolidayProvider;
import java.util.List;

public class FakeHolidayGenerator implements HolidayProvider {

    @Override
    public HolidayResponses generate() {
        return new HolidayResponses(
                new HolidayResponse(
                        new HolidayBody(
                                new HolidayItems(
                                        List.of(
                                                new HolidayItem(
                                                        "20250606"
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
