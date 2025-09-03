package finalmission.infrastructure.lesson;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.lesson.model.Name;
import org.springframework.data.repository.CrudRepository;

public interface JpaLessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByName(final Name name);
}
