package finalmission.domain.holiday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HolidayBody(
        @JsonProperty("items") HolidayItems items
) {
}
