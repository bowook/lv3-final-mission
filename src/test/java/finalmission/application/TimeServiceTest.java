package finalmission.application;

import finalmission.domain.holiday.repository.HolidayRepository;
import finalmission.domain.holiday.service.HolidayDomainService;
import finalmission.domain.time.entity.Time;
import finalmission.domain.time.exception.TimeNotAllowedException;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import finalmission.domain.time.repository.TimeRepository;
import finalmission.infrastructure.holiday.repository.FakeHolidayRepository;
import finalmission.infrastructure.time.repository.FakeTimeRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
public class TimeServiceTest {

    @Autowired
    private TimeService timeServiceTest;

    @Test
    void 시간_찾기() {
        // given
        final LocalDate localDate = LocalDate.of(2025, 6, 18);
        final LocalTime localTime = LocalTime.of(20, 20);
        final Date expectedDate = new Date(localDate);
        final StartAt expectedStartAt = new StartAt(localTime);

        // when
        final Time time = timeServiceTest.findTime(localDate, localTime);

        // then
        assertAll(
                () -> assertThat(time.getDate()).isEqualTo(expectedDate),
                () -> assertThat(time.getStartAt()).isEqualTo(expectedStartAt)
        );
    }

    @Test
    void 공휴일이면_시간_찾기_예외_발생() {
        // given
        final LocalDate localDate = LocalDate.of(2025, 6, 6);
        final LocalTime localTime = LocalTime.of(20, 20);

        // when & then
        Assertions.assertThatThrownBy(
                () -> timeServiceTest.findTime(localDate, localTime)
        ).isInstanceOf(TimeNotAllowedException.class);
    }

    @TestConfiguration
    static class TimeServiceTestConfiguration {

        @Bean
        public TimeRepository fakeTimeRepository() {
            return new FakeTimeRepository();
        }

        @Bean(name = "fakeHolidayRepositoryInTimeServiceTest")
        public HolidayRepository fakeHolidayRepository() {
            return new FakeHolidayRepository();
        }

        @Bean(name = "holidayDomainServiceTestInTimeServiceTest")
        public HolidayDomainService holidayDomainServiceTest() {
            return new HolidayDomainService(fakeHolidayRepository());
        }

        @Bean
        public TimeService timeServiceTest() {
            return new TimeService(fakeTimeRepository(), holidayDomainServiceTest());
        }
    }
}
