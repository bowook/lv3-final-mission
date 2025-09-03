package finalmission.domain.holiday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HolidayItem(
        @JsonProperty("locdate") String locdate
) {
}
