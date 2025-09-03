package finalmission.infrastructure.lesson.repository;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.lesson.model.Name;
import finalmission.domain.lesson.repository.LessonRepository;
import java.util.Map;

public class FakeLessonRepository implements LessonRepository {

    private final Map<Long, Lesson> database = Map.of(
            1L, new Lesson(
                    new Name("하체운동")
            )
    );

    @Override
    public Lesson findByName(final Name name) {
        return database.values()
                .stream()
                .filter(lesson -> lesson.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
