package finalmission.application;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.lesson.model.Name;
import finalmission.domain.lesson.repository.LessonRepository;
import finalmission.infrastructure.lesson.repository.FakeLessonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class LessonServiceTest {

    @Autowired
    private LessonService lessonServiceTest;

    @Test
    void 수업_찾기_테스트() {
        // given
        final String value = "하체운동";
        final Name name = new Name(value);

        // when
        final Lesson foundlesson = lessonServiceTest.findLesson(value);

        // then
        Assertions.assertThat(foundlesson.getName()).isEqualTo(name);
    }

    @TestConfiguration
    static class LessonServiceTestConfiguration {

        @Bean
        public LessonRepository fakeLessonRepository() {
            return new FakeLessonRepository();
        }

        @Bean
        public LessonService lessonServiceTest() {
            return new LessonService(fakeLessonRepository());
        }
    }
}
