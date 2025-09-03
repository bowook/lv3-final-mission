package finalmission.infrastructure.holiday;

import finalmission.domain.holiday.provider.HolidayProvider;
import finalmission.infrastructure.holiday.generator.HolidayGenerator;
import java.net.URI;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class HolidayRestClientConfig {

    private static final String CURRENT_YEAR = String.valueOf(LocalDate.now().getYear());
    private static final String CURRENT_MONTH = String.format("%02d", LocalDate.now().getMonthValue());

    private final String baseUrl;
    private final String serviceKey;

    public HolidayRestClientConfig(
            @Value("${openapi.public-holidays.base-url}") final String baseUrl,
            @Value("${openapi.public-holidays.service-key}") final String serviceKey) {
        this.baseUrl = baseUrl;
        this.serviceKey = serviceKey;
    }

    @Bean
    public HolidayProvider holidayGenerator() {
        final URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("solYear", CURRENT_YEAR)
                .queryParam("solMonth", CURRENT_MONTH)
                .queryParam("_type", "json")
                .build(true)
                .toUri();

        return new HolidayGenerator(uri);
    }
}
