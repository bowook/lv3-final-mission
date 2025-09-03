package finalmission.application;

import finalmission.domain.holiday.provider.HolidayProvider;
import finalmission.domain.holiday.repository.HolidayRepository;
import finalmission.infrastructure.holiday.generator.FakeHolidayGenerator;
import finalmission.infrastructure.holiday.repository.FakeHolidayRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AdminServiceTest {

    @Autowired
    private AdminService adminServiceTest;

    @Test
    void 휴일_저장() {
        // when & then
        Assertions.assertThatCode(() -> adminServiceTest.saveHoliday())
                .doesNotThrowAnyException();
    }

    @TestConfiguration
    static class AdminServiceTestConfiguration {
        @Bean
        public HolidayProvider fakeHolidayProvider() {
            return new FakeHolidayGenerator();
        }

        @Bean(name = "fakeHolidayRepositoryInAdminServiceTest")
        public HolidayRepository fakeHolidayRepository() {
            return new FakeHolidayRepository();
        }

        @Bean
        public AdminService adminServiceTest() {
            return new AdminService(fakeHolidayProvider(), fakeHolidayRepository());
        }
    }
}
