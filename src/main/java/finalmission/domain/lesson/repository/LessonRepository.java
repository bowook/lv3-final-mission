package finalmission.domain.lesson.repository;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.lesson.model.Name;

public interface LessonRepository {

    Lesson findByName(final Name name);
}
