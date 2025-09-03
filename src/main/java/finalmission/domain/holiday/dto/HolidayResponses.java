package finalmission.domain.holiday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HolidayResponses(
        @JsonProperty("response") HolidayResponse response
) {
}
