package finalmission.infrastructure.holiday.generator;

import finalmission.domain.holiday.dto.HolidayResponses;
import finalmission.domain.holiday.provider.HolidayProvider;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class HolidayGenerator implements HolidayProvider {

    private final URI uri;

    @Override
    public HolidayResponses generate() {
        return RestClient.builder()
                .build()
                .get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(HolidayResponses.class);
    }
}
