package finalmission.domain.holiday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record HolidayItems(
        @JsonProperty("item") List<HolidayItem> item
) {
}
